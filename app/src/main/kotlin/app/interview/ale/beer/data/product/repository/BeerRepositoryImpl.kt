package app.interview.ale.beer.data.product.repository

import app.interview.ale.beer.data.local.room.BeerDAO
import app.interview.ale.beer.data.product.retrofit.BeerApiService
import app.interview.ale.beer.domain.entities.Beer
import app.interview.ale.beer.domain.entities.BeerPage
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(private val localDb: BeerDAO, private val beerService: BeerApiService) : BeerRepository {
    override suspend fun fetchBeers(page: Int, limit: Int): BeerPage = beerService.getBeers(page, limit)

    override suspend fun getAll(): Flow<List<Beer>> = localDb.getAll()

    override val favoriteBeers: Flow<List<Beer>> = localDb.getAll()

    override suspend fun addToFavorite(beer: Beer) {
        Timber.d("add: $beer")
        localDb.add(beer)
    }

    override suspend fun updateFavorite(beer: Beer): Int {
        Timber.d("update: $beer")
        return localDb.update(beer)
    }

    override suspend fun deleteFavorite(beer: Beer): Int {
        Timber.d("remove favorite item: $beer")
        return localDb.delete(beer)
    }

    override suspend fun isExist(id: Int): Boolean {
        return localDb.isExist(id)
    }

    override suspend fun returnIfExist(id: Int): Beer? {
        return localDb.returnIfExist(id)
    }
}