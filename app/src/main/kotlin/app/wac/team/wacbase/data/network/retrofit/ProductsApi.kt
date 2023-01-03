package app.wac.team.wacbase.data.network.retrofit

import app.wac.team.wacbase.data.network.common.BaseApiService
import app.wac.team.wacbase.data.network.common.GenericNetworkResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi : BaseApiService {

    @GET("beers")
    fun getProductsList(
        /*@Query("ids") ids: String,*/
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 40,
    ): Single<GenericNetworkResponse<List<String>>>

    @GET("beers")
    suspend fun getProductsListByCoroutine(
        /*@Query("ids") ids: String,*/
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 40,
    ): List<String>

}