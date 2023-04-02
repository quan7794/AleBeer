package app.wac.team.wacbase.di.module

import app.wac.team.wacbase.data.product.repository.ProductRepository
import app.wac.team.wacbase.data.product.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository

}