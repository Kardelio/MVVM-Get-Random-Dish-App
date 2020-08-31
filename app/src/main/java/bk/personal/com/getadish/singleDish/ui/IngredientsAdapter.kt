package bk.personal.com.getadish.singleDish.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bk.personal.com.getadish.R
import bk.personal.com.getadish.randomDish.model.Ingredient

class IngredientsAdapter() :
    ListAdapter<Ingredient, IngredientsAdapter.IngredientViewHolder>(IngredientDiffCallback()) {

    class IngredientDiffCallback : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean =
            oldItem == newItem
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        getItem(position)?.let {
            holder.data = it
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder(parent)
    }

    inner class IngredientViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.element_ingredient, parent, false
        )
    ) {
        var data: Ingredient? = null
            set(value) {
                field = value
                setupUI()
            }

        private fun setupUI() {
            data?.let { ing ->
                itemView.findViewById<TextView>(R.id.ingredient_name).text = ing.name
                itemView.findViewById<TextView>(R.id.ingredient_amount).text = ing.measurement
            }
        }
    }
}