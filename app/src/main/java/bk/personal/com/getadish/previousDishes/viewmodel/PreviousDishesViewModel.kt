package bk.personal.com.getadish.previousDishes.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import bk.personal.com.getadish.randomDish.model.Dish
import bk.personal.com.getadish.IDishRepository

class PreviousDishesViewModel @ViewModelInject constructor(private val repo: IDishRepository) : ViewModel() {

    val previousDishes: LiveData<List<Dish>> = repo.getAllPreviousDishes()

}