package app.interview.ale.beer.data.product.datasource

import app.interview.ale.beer.domain.entities.Beer
import kotlinx.coroutines.flow.Flow

interface BeerDataSource {
    suspend fun getAll(): Flow<List<Beer>>
    suspend fun add(beer: Beer)
    suspend fun isExist(searchQuery: String): Boolean
}