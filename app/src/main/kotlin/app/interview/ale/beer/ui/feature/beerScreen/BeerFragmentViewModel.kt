package app.interview.ale.beer.ui.feature.beerScreen

import android.view.View
import androidx.lifecycle.viewModelScope
import app.interview.ale.base.common.BaseViewModel
import app.interview.ale.beer.data.product.ProductConstant.DEFAULT_BEER_PAGE_LIMIT
import app.interview.ale.beer.data.product.repository.BeerRepository
import app.interview.ale.beer.domain.entities.Beer
import app.interview.ale.beer.domain.entities.BeerPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BeerFragmentViewModel @Inject constructor(private val beerRepository: BeerRepository) : BaseViewModel() {
    private val _pageIndex = MutableStateFlow(0)
    val pageIndex = _pageIndex.asStateFlow()

    private val _currentPage = MutableSharedFlow<BeerPage?>(0, 1)
    val currentPage = _currentPage.asSharedFlow()

//    private val _beers = MutableStateFlow<MutableList<Beer>>(mutableListOf())
//    val beers = _beers.asStateFlow()

    private var allowToFetchNextPage = true

    init {
        viewModelScope.launch {
            fetchPage()
        }
    }

    suspend fun fetchPage(toNextPage: Boolean = true, limit: Int = DEFAULT_BEER_PAGE_LIMIT) {
        if (toNextPage && !allowToFetchNextPage) uiSingleEvent.postValue(BeerUiState.FetchError("Nothing to load")).also { return }
        val fetchIndex = if (toNextPage) _pageIndex.value + 1 else 1
        Timber.d("$fetchIndex, to next page = $toNextPage")
        _pageIndex.tryEmit(fetchIndex)
        val pageResult = beerRepository.fetchBeers(fetchIndex, limit)
        allowToFetchNextPage = pageResult.loadMore
        _currentPage.tryEmit(pageResult)
//        updateBeers(pageResult)
    }

//    private fun updateBeers(pageResult: BeerPage) {
//        val currentBeers = _beers.value.toMutableList()
//        currentBeers.addAll(pageResult.beers)
//        _beers.tryEmit(currentBeers)
//    }

    override fun onClick(view: View) {
        when (view.id) {
        }
    }
}