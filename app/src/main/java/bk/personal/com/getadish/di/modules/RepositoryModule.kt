package bk.personal.com.getadish.di.modules

import bk.personal.com.getadish.DishRepository
import bk.personal.com.getadish.IDishRepository
import bk.personal.com.getadish.ISingleDishRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepo(repo: DishRepository): IDishRepository

    @Binds
    fun bindRepoSingleDish(repo: DishRepository): ISingleDishRepository
}