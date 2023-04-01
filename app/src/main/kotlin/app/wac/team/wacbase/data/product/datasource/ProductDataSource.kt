package app.wac.team.wacbase.data.product.datasource

import app.wac.team.wacbase.domain.entities.Product
import kotlinx.coroutines.flow.Flow

interface ProductDataSource {
    suspend fun getAll(): Flow<List<Product>>
    suspend fun insertProduct(product: Product)
    suspend fun isExist(searchQuery: String): Boolean
}