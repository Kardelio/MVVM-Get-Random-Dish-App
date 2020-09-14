package bk.personal.com.getadish.randomDish.model

import androidx.room.TypeConverter
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class DishConverter {
    companion object {
        const val list_splitter = "==="
        const val ingredient_splitter = ":::"
    }

    @TypeConverter
    fun toIngredientList(str: String): List<Ingredient> {
        val listOut = mutableListOf<Ingredient>()
        val arrayOfIngs = str.split(list_splitter)
        for (ing in arrayOfIngs) {
            if (!ing.isNullOrBlank()) {
                val split = ing.split(ingredient_splitter)
                listOut.add(Ingredient(split[0], split[1]))
            }
        }
        return listOut
    }

    @TypeConverter
    fun fromIngredientList(list: List<Ingredient>): String {
        var result = ""
        for (item in list) {
            result += "${item.name}${ingredient_splitter}${item.measurement}${list_splitter}"
        }
        return result
    }
}

class DishDeserializer : JsonDeserializer<Dish> {
    private fun getJsonString(jsonObject: JsonObject, key: String, default: String = ""): String {
        if (!jsonObject.get(key).isJsonNull) {
            return jsonObject.get(key).asString ?: default
        }
        return default
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Dish {
        try {
            json?.let {
                val jsonObject = it.asJsonObject
                val id = getJsonString(jsonObject, "idMeal")
                val name = getJsonString(jsonObject, "strMeal")
                val ingredients = mutableListOf<Ingredient>()
                val category = getJsonString(jsonObject, "strCategory")
                val area = getJsonString(jsonObject, "strArea")
                val instructions = getJsonString(jsonObject, "strInstructions")
                val thumbnail = getJsonString(jsonObject, "strMealThumb")
                val tags = getJsonString(jsonObject,"strTags")
                val youtube = getJsonString(jsonObject,"strYoutube")
                val source = getJsonString(jsonObject,"strSource")
                for (i in 1..20) {
                    val ing = getJsonString(jsonObject,"strIngredient${i}")
                    val measure =  getJsonString(jsonObject,"strMeasure${i}")
                    if (!ing.isBlank() && !measure.isBlank()) {
                        ingredients.add(Ingredient(ing, measure))
                    }
                }
                return Dish(
                    id,
                    name,
                    category,
                    area,
                    instructions,
                    thumbnail,
                    tags,
                    youtube,
                    source,
                    ingredients,
                    System.currentTimeMillis() / 1000
                )
            } ?: return Dish()
        } catch (e: Exception) {
            return Dish()
        }
    }
}
