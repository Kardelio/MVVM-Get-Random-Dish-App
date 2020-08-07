package bk.personal.com.getadish.previousDishes.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bk.personal.com.getadish.R
import bk.personal.com.getadish.previousDishes.viewmodel.PreviousDishesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_previous_dishes.view.*

@AndroidEntryPoint
class PreviousDishFragment : Fragment() {

    private lateinit var previousDishesAdapter: PreviousDishesAdapter
    private val viewmodel: PreviousDishesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_previous_dishes, container, false)

        previousDishesAdapter = PreviousDishesAdapter()

        v.previous_dishes_rv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = previousDishesAdapter
        }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.previousDishes.observe(viewLifecycleOwner, Observer {
            previousDishesAdapter.submitList(it)
        })
    }
}