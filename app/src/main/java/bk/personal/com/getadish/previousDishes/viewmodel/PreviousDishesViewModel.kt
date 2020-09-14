package bk.personal.com.getadish.previousDishes.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bk.personal.com.getadish.randomDish.model.Dish
import bk.personal.com.getadish.sharedRepository.IDishRepository
import kotlinx.coroutines.launch

class PreviousDishesViewModel @ViewModelInject constructor(private val repo: IDishRepository) : ViewModel() {

    val previousDishes: LiveData<List<Dish>> = repo.getAllPreviousDishes()

    fun updateDish(dish: Dish){
        viewModelScope.launch {
            repo.updateDish(dish)
        }
    }

    fun deleteDish(dish: Dish){
        viewModelScope.launch {
            repo.deleteADish(dish)
        }
    }
}