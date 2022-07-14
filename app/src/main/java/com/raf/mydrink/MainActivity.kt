package com.raf.mydrink

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.raf.mydrink.core.ui.DrinkAdapter
import com.raf.mydrink.databinding.ActivityMainBinding
import com.raf.mydrink.detail.DetailActivity
import com.raf.mydrink.setting.SettingActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabFavorite.setOnClickListener {
            val uri = Uri.parse("drinkapp://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        showAllDrink()
    }

    private fun showAllDrink() {
        val drinkAdapter = DrinkAdapter()
        drinkAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        viewModel.drink.observe(this) { drink ->
            if (drink != null) {
                when (drink) {
                    is com.raf.mydrink.core.data.Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is com.raf.mydrink.core.data.Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        drinkAdapter.setData(drink.data!!)
                    }
                    is com.raf.mydrink.core.data.Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = drink.message ?: getString(R.string.something_wrong)
                    }

                }
            }
        }

        with(binding.rvDrink) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = drinkAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_setting -> startActivity(Intent(this, SettingActivity::class.java))
            else -> null
        } ?: return super.onOptionsItemSelected(item)
        return true
    }

    override fun onStop() {
        super.onStop()
        showAllDrink()
    }
}