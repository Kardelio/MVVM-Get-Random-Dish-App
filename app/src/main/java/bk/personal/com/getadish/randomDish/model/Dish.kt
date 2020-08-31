package bk.personal.com.getadish.randomDish.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dish_table")
data class Dish(
    @PrimaryKey
    val id: String = "",
    val name: String? = "",
    val category: String? = "",
    val area: String? = "",
    val instructions: String? = "",
    val thumbnail: String? = "",
    val tags: String? = "",
    val youtubeLink: String? = "",
    val source: String? = "",
    val ingredients: List<Ingredient> = emptyList()
)

data class Ingredient(
    val name: String = "",
    val measurement: String = ""
)

class Meals(
    val meals: List<Dish> = emptyList()
)
