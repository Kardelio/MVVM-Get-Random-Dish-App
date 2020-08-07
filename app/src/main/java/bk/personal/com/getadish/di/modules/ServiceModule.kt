package bk.personal.com.getadish.di.modules

import bk.personal.com.getadish.randomDish.model.Dish
import bk.personal.com.getadish.randomDish.model.DishDeserializer
import bk.personal.com.getadish.randomDish.service.IRandomDishService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Dish::class.java, DishDeserializer())
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideRandomDishService(apiInterface: Retrofit): IRandomDishService {
        return apiInterface.create(IRandomDishService::class.java)
    }

}