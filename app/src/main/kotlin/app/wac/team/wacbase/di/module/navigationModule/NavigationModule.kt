package app.wac.team.wacbase.di.module.navigationModule

import app.wac.team.wacbase.di.module.navigationModule.AppNavigator
import app.wac.team.wacbase.di.module.navigationModule.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class, ActivityComponent::class)
@Module
abstract class NavigationModule {

    @Binds
    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator

}