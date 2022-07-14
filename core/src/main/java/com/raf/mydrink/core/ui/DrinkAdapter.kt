package com.raf.mydrink.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raf.mydrink.core.R
import com.raf.mydrink.core.databinding.ItemListDrinkBinding
import com.raf.mydrink.core.domain.model.Drink

class DrinkAdapter : RecyclerView.Adapter<DrinkAdapter.ListViewHolder>() {

    private var listData = ArrayList<Drink>()
    var onItemClick: ((Drink) -> Unit)? = null

    fun setData(newListData: List<Drink>) {
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_drink, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListDrinkBinding.bind(itemView)
        fun bind(data: Drink) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.drinkImg)
                    .into(itemImg)
                itemName.text = data.drinkName
                itemCategory.text = data.drinkCategory
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}