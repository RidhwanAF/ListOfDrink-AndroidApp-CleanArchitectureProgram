package com.raf.mydrink.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.raf.mydrink.R
import com.raf.mydrink.core.ui.DrinkAdapter
import com.raf.mydrink.detail.DetailActivity
import com.raf.mydrink.favorite.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.favorite_drink)

        loadKoinModules(favoriteModule)
        showFavoriteData()
    }

    private fun showFavoriteData() {
        val favAdapter = DrinkAdapter()
        favAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        viewModel.favoriteDrink.observe(this) {
            favAdapter.setData(it)
            binding.emptyDataFavorite.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvDrink) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}