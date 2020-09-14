package bk.personal.com.getadish.randomDish.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import bk.personal.com.getadish.R
import bk.personal.com.getadish.randomDish.model.Dish
import bk.personal.com.getadish.randomDish.viewmodel.RandomDishViewModel
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomDishFragment : Fragment() {

    private val viewModel: RandomDishViewModel by viewModels()
    private lateinit var motionLayout: MotionLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_random_dish, container, false)
        motionLayout = v.findViewById(R.id.randomDishMotionLayout)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<LottieAnimationView>(R.id.boilingPotView).setOnClickListener {
            motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                }

                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                }

                override fun onTransitionTrigger(
                    p0: MotionLayout?,
                    p1: Int,
                    p2: Boolean,
                    p3: Float
                ) {
                }

                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    viewModel.getRandomDish()
                }
            })
            motionLayout.transitionToEnd()
//            viewModel.getRandomDish()
        }

        view.findViewById<ConstraintLayout>(R.id.randomDishMotionLayout).setOnClickListener {
            viewModel.currentDish.value?.let {
                findNavController().navigate(
                    RandomDishFragmentDirections.actionRandomDishFragmentToSingleDishFragment(
                        it.id
                    )
                )
            }
        }

        viewModel.currentDish.observe(viewLifecycleOwner, Observer {
            loadDishIntoView(it)
        })
    }

    private fun loadDishIntoView(dish: Dish) {
        view?.let {
//            it.findViewById<TextView>(R.id.dishTitleText).text = dish.name
            Glide.with(this)
                .load(dish.thumbnail)
                .placeholder(R.drawable.simple_placeholder)
                .into(it.findViewById(R.id.dishImageView))
        }
    }

}