package app.interview.ale.beer.data.product.repository

import app.interview.ale.beer.data.product.ProductConstant.DEFAULT_BEER_PAGE_LIMIT
import app.interview.ale.beer.domain.entities.Beer
import app.interview.ale.beer.domain.entities.BeerPage
import kotlinx.coroutines.flow.Flow

interface BeerRepository {
    suspend fun fetchBeers(page: Int = 1, limit: Int = DEFAULT_BEER_PAGE_LIMIT): BeerPage
    suspend fun getAll(): Flow<List<Beer>>
    val favoriteBeers: Flow<List<Beer>>
    suspend fun addToFavorite(beer: Beer)
    suspend fun updateFavorite(beer: Beer): Int
    suspend fun deleteFavorite(beer: Beer): Int
    suspend fun isExist(id: Int): Boolean
    suspend fun returnIfExist(id: Int): Beer?
}