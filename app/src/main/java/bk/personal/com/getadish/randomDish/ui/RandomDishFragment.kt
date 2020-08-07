package bk.personal.com.getadish.randomDish.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import bk.personal.com.getadish.R
import bk.personal.com.getadish.randomDish.model.Dish
import bk.personal.com.getadish.randomDish.viewmodel.RandomDishViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomDishFragment : Fragment(){

    private val viewModel: RandomDishViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random_dish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.getRandomDishButton).setOnClickListener {
            viewModel.getRandomDish()
        }

        view.findViewById<LinearLayout>(R.id.randomDishContainer).setOnClickListener {
            viewModel.currentDish.value?.let {
                findNavController().navigate(RandomDishFragmentDirections.actionRandomDishFragmentToSingleDishFragment(it.id))
            }
        }

        viewModel.currentDish.observe(viewLifecycleOwner, Observer {
            loadDishIntoView(it)
        })
    }

    private fun loadDishIntoView(dish: Dish){
        view?.let {
            it.findViewById<TextView>(R.id.dishTitleText).text = dish.name
            Glide.with(this)
                .load(dish.thumbnail)
                .placeholder(R.drawable.simple_placeholder)
                .into(it.findViewById(R.id.dishImageView))
        }
    }

}