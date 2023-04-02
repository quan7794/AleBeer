package app.interview.ale.beer.di.module

import app.interview.ale.beer.data.product.retrofit.BeerApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun beerApiService(retrofit: Retrofit): BeerApiService =
        retrofit.create(BeerApiService::class.java)

}