package bk.personal.com.getadish.singleDish.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bk.personal.com.getadish.R
import bk.personal.com.getadish.previousDishes.ui.PreviousDishesAdapter
import bk.personal.com.getadish.randomDish.model.Dish
import bk.personal.com.getadish.singleDish.viewmodel.SingleDishViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_single_dish.view.*

@AndroidEntryPoint
class SingleDishFragment : Fragment() {

    lateinit var args: SingleDishFragmentArgs
    private val viewModel: SingleDishViewModel by viewModels()
    private lateinit var ingredientsAdapter: IngredientsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            args = SingleDishFragmentArgs.fromBundle(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_single_dish, container, false)

        ingredientsAdapter = IngredientsAdapter()

        v.single_dish_ingredients.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = ingredientsAdapter
        }

        return v
    }

    override fun onResume() {
        super.onResume()
        viewModel.setCurrentDishId(args.dishId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currentDish.observe(viewLifecycleOwner, Observer {
            setSingleDishUI(it)
        })
    }

    private fun setSingleDishUI(dish: Dish) {
        view?.let {
            it.findViewById<TextView>(R.id.single_dish_title).text = dish.name
            it.findViewById<TextView>(R.id.single_dish_category).text = dish.category
            it.findViewById<TextView>(R.id.single_dish_area).text = dish.area
            it.findViewById<TextView>(R.id.single_dish_instructions_text).text = dish.instructions
            ingredientsAdapter.submitList(dish.ingredients)
            Glide.with(it)
                .load(dish.thumbnail)
                .into(it.findViewById(R.id.single_dish_image))
        }
    }

}