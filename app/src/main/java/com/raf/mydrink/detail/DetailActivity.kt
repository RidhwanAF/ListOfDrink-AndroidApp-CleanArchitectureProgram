package com.raf.mydrink.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.raf.mydrink.R
import com.raf.mydrink.core.domain.model.Drink
import com.raf.mydrink.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailDrink = intent.getParcelableExtra<Drink>(EXTRA_DATA)
        showDetailDrink(detailDrink)
    }

    private fun showDetailDrink(detailDrink: Drink?) {
        detailDrink?.let {
            supportActionBar?.title = detailDrink.drinkName
            binding.content.tvCategory.text = detailDrink.drinkCategory
            binding.content.tvInstructor.text = detailDrink.drinkInstruction
            Glide.with(this)
                .load(detailDrink.drinkImg)
                .into(binding.imgDrink)

            var statusFav = detailDrink.isFavorite
            setStateFav(statusFav)
            binding.fab.setOnClickListener{
                statusFav = !statusFav
                viewModel.setFavoriteDrink(detailDrink, statusFav)
                setStateFav(statusFav)
            }
        }
    }

    private fun setStateFav(stateFav: Boolean) {
        if (stateFav) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onStop() {
        super.onStop()
        showDetailDrink(null)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}