package app.interview.ale.beer.data.product.retrofit

import app.interview.ale.beer.data.network.common.BaseApiService
import app.interview.ale.beer.data.product.ProductConstant.DEFAULT_BEER_PAGE_LIMIT
import app.interview.ale.beer.domain.entities.BeerPage
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApiService : BaseApiService {

    @GET("sample-data")
    suspend fun getBeers(
        @Query("page") page: Int = 1,
        @Query("limit") perPage: Int = DEFAULT_BEER_PAGE_LIMIT,
    ): BeerPage

}