package bk.personal.com.getadish.singleDish.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import bk.personal.com.getadish.R
import bk.personal.com.getadish.singleDish.viewmodel.SingleDishViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleDishFragment : Fragment(){


    lateinit var args: SingleDishFragmentArgs

    private val viewModel: SingleDishViewModel by viewModels()

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
        return inflater.inflate(R.layout.fragment_frag, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.setCurrentDishId(args.dishId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currentDish.observe(viewLifecycleOwner, Observer {
            //TODO
            Log.d("Bk","$it")
        })
    }

}