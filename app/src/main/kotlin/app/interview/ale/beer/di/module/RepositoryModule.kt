package app.interview.ale.beer.di.module

import app.interview.ale.beer.data.product.repository.BeerRepository
import app.interview.ale.beer.data.product.repository.BeerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindBeerRepository(beerRepositoryImpl: BeerRepositoryImpl): BeerRepository

}