package bk.personal.com.getadish.db

import androidx.lifecycle.LiveData
import androidx.room.*
import bk.personal.com.getadish.randomDish.model.Dish

@Dao
interface DishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDish(dish: Dish): Long

    @Delete
    suspend fun deleteDish(dish: Dish)

    @Query("SELECT * FROM dish_table")
    suspend fun getAllDishes(): List<Dish>

    @Query("SELECT * FROM dish_table WHERE id = :id")
    suspend fun getDish(id: String): Dish

    @Query("SELECT * FROM dish_table ORDER BY timestamp DESC")
    fun getPreviousDishes(): LiveData<List<Dish>>

    @Query("SELECT * FROM dish_table WHERE id = :id")
    fun getSpecificDish(id: String): LiveData<Dish>
}

