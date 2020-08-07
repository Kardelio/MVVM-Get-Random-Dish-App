package bk.personal.com.getadish.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import bk.personal.com.getadish.GetADishApplication.Companion.SHARED_PREFERENCE_KEY
import bk.personal.com.getadish.db.DishDao
import bk.personal.com.getadish.db.GetADishDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext app: Context): GetADishDatabase {
        return Room.databaseBuilder(app, GetADishDatabase::class.java, "dish.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRunDao(db: GetADishDatabase): DishDao {
        return db.getDishDao()
    }

    @Singleton
    @Provides
    fun provideDefaultSharedPreferences(context: Application) =
        context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
}