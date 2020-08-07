package bk.personal.com.getadish.singleDish.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bk.personal.com.getadish.ISingleDishRepository
import bk.personal.com.getadish.randomDish.model.Dish
import kotlinx.coroutines.launch

class SingleDishViewModel @ViewModelInject constructor(val repo: ISingleDishRepository): ViewModel(){

    val currentDish = MutableLiveData<Dish>()

    fun setCurrentDishId(id: String){
        viewModelScope.launch {
            val dish = repo.getSpecificDish(id)
            currentDish.postValue(dish)
        }
    }

}