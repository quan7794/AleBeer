package app.wac.team.wacbase.data.product.repository

import app.wac.team.wacbase.domain.entities.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getAll(): Flow<List<Product>>
    suspend fun insertProduct(product: Product)
    suspend fun isExist(searchQuery: String): Boolean
}