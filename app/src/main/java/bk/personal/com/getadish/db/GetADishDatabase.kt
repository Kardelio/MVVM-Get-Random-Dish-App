package bk.personal.com.getadish.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import bk.personal.com.getadish.randomDish.model.Dish
import bk.personal.com.getadish.randomDish.model.DishConverter

@Database(
    entities = [Dish::class],
    version = 1, exportSchema = false
)
@TypeConverters(DishConverter::class)
abstract class GetADishDatabase : RoomDatabase() {

    abstract fun getDishDao(): DishDao
}
