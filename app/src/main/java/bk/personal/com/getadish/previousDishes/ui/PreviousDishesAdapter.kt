package bk.personal.com.getadish.previousDishes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bk.personal.com.getadish.R
import bk.personal.com.getadish.randomDish.model.Dish
import com.bumptech.glide.Glide

class PreviousDishesAdapter :
    ListAdapter<Dish, PreviousDishesAdapter.DishViewHolder>(DishDiffCallback()) {

    class DishDiffCallback : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean =
            oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        return DishViewHolder(parent)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        getItem(position)?.let {
            holder.data = it
        }
    }

    inner class DishViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.element_previous_dish, parent, false
        )
    ) {
        private val tv = itemView.findViewById<TextView>(R.id.previousDishTitle)
        private val iv = itemView.findViewById<ImageView>(R.id.previousDishImage)

        var data: Dish? = null
            set(value) {
                field = value
                configureUI()
            }

        private fun configureUI() {
            data?.let {
                tv.text = it.name
                Glide.with(itemView)
                    .load(it.thumbnail)
                    .into(iv)
            }
        }
    }

}