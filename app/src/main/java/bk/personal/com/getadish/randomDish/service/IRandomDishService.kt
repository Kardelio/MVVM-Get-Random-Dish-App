package bk.personal.com.getadish.randomDish.service

import bk.personal.com.getadish.randomDish.model.Meals
import retrofit2.http.GET

//NOTE to self: Scratch file contains example response
interface IRandomDishService {

    @GET("random.php")
    suspend fun getRandomDish(): Meals

}
