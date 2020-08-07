package bk.personal.com.getadish.di.modules

import bk.personal.com.getadish.config.AppStatusModel
import bk.personal.com.getadish.config.IAppStatusModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface ConfigModule {
    @Binds
    fun provideAppStatusModel(appStatusModel: AppStatusModel): IAppStatusModel
}