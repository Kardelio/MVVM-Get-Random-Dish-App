package bk.personal.com.getadish.randomDish.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bk.personal.com.getadish.config.IAppStatusModel
import bk.personal.com.getadish.randomDish.model.Dish
import bk.personal.com.getadish.IDishRepository
import bk.personal.com.getadish.utils.DateUtils
import kotlinx.coroutines.launch

class RandomDishViewModel @ViewModelInject constructor(val appStatusModel: IAppStatusModel, val repo: IDishRepository) :
    ViewModel() {

    val currentDish = MutableLiveData<Dish>()
//    val buttonEnabled = MutableLiveData<Boolean>()

    //Taskmanager?

    //Could do a timertask thing, for scneraio whilst view still with us
    //SP are observable...

    //job for the alarm manager that will be aquick service that will update the pref at certain time
    //

    fun getRandomDish() {
        appStatusModel.lastTimeStampButtonPressed = DateUtils.getCurrentTimeStamp()
        viewModelScope.launch {
            val dish = repo.getARandomDish()
            currentDish.postValue(dish)
        }
    }
}