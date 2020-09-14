package bk.personal.com.getadish.previousDishes.ui

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bk.personal.com.getadish.R
import bk.personal.com.getadish.previousDishes.viewmodel.PreviousDishesViewModel
import bk.personal.com.getadish.randomDish.model.Dish
import bk.personal.com.getadish.randomDish.ui.RandomDishFragmentDirections
import bk.personal.com.getadish.utils.px
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_previous_dishes.view.*

@AndroidEntryPoint
class PreviousDishFragment : Fragment(), PreviousDishClick {

    private lateinit var previousDishesAdapter: PreviousDishesAdapter
    private var rv: RecyclerView? = null
    private val viewmodel: PreviousDishesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_previous_dishes, container, false)

        previousDishesAdapter = PreviousDishesAdapter(this)

        val aa =
            AnimationUtils.loadLayoutAnimation(this.context, R.anim.layout_animation_from_right)
        rv = v.previous_dishes_rv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = previousDishesAdapter
            layoutAnimation = aa
        }

        val itemTouchHelper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

                val iconMargin = 16.px

                val background =
                    ColorDrawable(ContextCompat.getColor(context!!, R.color.warning_red))
                val icon = ContextCompat.getDrawable(context!!, R.drawable.ic_delete)

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean = false

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                    val pos = viewHolder.adapterPosition
                    previousDishesAdapter.getItemId(pos)
                    viewmodel.deleteDish(previousDishesAdapter.getItem(pos))
                   //TODO undo button
//                    Snackbar.make(v, "Blah", Snackbar.LENGTH_LONG).show()
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    if (dX < 0) {
                        background.setBounds(
                            viewHolder.itemView.right + dX.toInt(),
                            viewHolder.itemView.top,
                            viewHolder.itemView.right,
                            viewHolder.itemView.bottom
                        )
                        background.draw(c)

                        icon?.apply {
                            val iconSize = icon.intrinsicHeight
                            val halfIcon = iconSize / 2
                            val top =
                                viewHolder.itemView.top + ((viewHolder.itemView.bottom - viewHolder.itemView.top) / 2 - halfIcon)
                            setBounds(
                                viewHolder.itemView.right - (iconMargin + icon.intrinsicWidth),
                                top,
                                viewHolder.itemView.right - iconMargin,
                                top + icon.intrinsicHeight
                            )
                            draw(c)
                        }
                    }
                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }
            }
        )
        itemTouchHelper.attachToRecyclerView(rv)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.previousDishes.observe(viewLifecycleOwner, Observer {
            previousDishesAdapter.submitList(it)
//            rv?.scheduleLayoutAnimation()
        })
    }

    override fun clickPreviousDish(dish: Dish) {
        Log.d("BK", "clicked: ${dish.name}")
        findNavController().navigate(
            PreviousDishFragmentDirections.actionPreviousDishFragmentToSingleDishFragment(
                dish.id
            )
        )
    }

    override fun updateFav(dish: Dish) {
        viewmodel.updateDish(dish)
    }
}