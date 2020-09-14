package bk.personal.com.getadish.previousDishes.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bk.personal.com.getadish.R
import bk.personal.com.getadish.randomDish.model.Dish
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide

interface PreviousDishClick {
    fun clickPreviousDish(dish: Dish)
    fun updateFav(dish: Dish)
}

class PreviousDishesAdapter(private val callback : PreviousDishClick) :
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

    public override fun getItem(position: Int): Dish {
        return super.getItem(position)
    }

    inner class DishViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.element_previous_dish, parent, false
        )
    ) {
        private val tv = itemView.findViewById<TextView>(R.id.previousDishTitle)
        private val iv = itemView.findViewById<ImageView>(R.id.previousDishImage)
        private val textBlock = itemView.findViewById<LinearLayout>(R.id.previousDishTextBlock)
//        private val heart = itemView.findViewById<ToggleButton>(R.id.favourite_toggle)
        private val heart = itemView.findViewById<LottieAnimationView>(R.id.favourite_toggle)

        var data: Dish? = null
            set(value) {
                field = value
                configureUI()
            }

        private fun configureUI() {
            data?.let {dish ->
                setHeart(dish.favourited, false)
//                heart.isChecked = dish.favourited
                tv.text = dish.name
                Glide.with(itemView)
                    .load(dish.thumbnail)
                    .into(iv)
                iv.setOnClickListener {
                    itemClicked(dish)
                }
                textBlock.setOnClickListener {
                    itemClicked(dish)
                }
                heart.setOnClickListener {
                    dish.favourited = !dish.favourited
                    Log.d("Bk","heart clicked ${dish.favourited}")
                    setHeart(dish.favourited, true)
                    callback.updateFav(dish)
                }
//                heart.setOnCheckedChangeListener { buttonView, isChecked ->
//                    Log.d("Bk","heart clicked $isChecked")
//                    dish.favourited = isChecked
//                    callback.updateFav(dish)
//                }
            }
        }

        private fun setHeart(faved: Boolean, animate: Boolean){
            if(faved){
                if(animate){
                    heart.setMinAndMaxFrame(40,60)
                    heart.playAnimation()
                } else {
                    heart.frame = 60
                }
            } else {
                if(animate){
                    heart.setMinAndMaxFrame(105, 125)
                    heart.playAnimation()
                } else {
                    heart.frame = 0
                }
            }
        }

        private fun itemClicked(dish: Dish){
            callback.clickPreviousDish(dish)
        }
    }

}