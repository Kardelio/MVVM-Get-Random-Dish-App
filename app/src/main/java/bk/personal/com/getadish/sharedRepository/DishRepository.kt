package bk.personal.com.getadish.sharedRepository

import androidx.lifecycle.LiveData
import bk.personal.com.getadish.db.DishDao
import bk.personal.com.getadish.randomDish.model.Dish
import bk.personal.com.getadish.randomDish.service.IRandomDishService
import javax.inject.Inject

interface IDishRepository {
    suspend fun getARandomDish(): Dish
    fun getAllPreviousDishes(): LiveData<List<Dish>>
    suspend fun updateDish(singleDish: Dish)
    suspend fun deleteADish(dish: Dish)
}

interface ISingleDishRepository{
    suspend fun getSpecificDish(id: String): Dish
}

class DishRepository @Inject constructor(
    private val db: DishDao,
    private val service: IRandomDishService
) : IDishRepository, ISingleDishRepository {
    override suspend fun getARandomDish(): Dish {
        val singleDish = service.getRandomDish().meals[0]
        db.addDish(singleDish)
        return singleDish
    }

    override fun getAllPreviousDishes(): LiveData<List<Dish>> {
        return db.getPreviousDishes()
    }

    override suspend fun getSpecificDish(id: String): Dish {
        return db.getDish(id)
   }

    override suspend fun updateDish(singleDish: Dish) {
        db.addDish(singleDish)
    }

    override suspend fun deleteADish(dish: Dish) {
        db.deleteDish(dish)
    }
}