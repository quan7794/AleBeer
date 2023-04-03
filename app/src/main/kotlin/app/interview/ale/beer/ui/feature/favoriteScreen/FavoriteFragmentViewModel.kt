package app.interview.ale.beer.ui.feature.favoriteScreen

import android.view.View
import androidx.lifecycle.viewModelScope
import app.interview.ale.base.common.BaseViewModel
import app.interview.ale.beer.data.product.repository.BeerRepository
import app.interview.ale.beer.domain.entities.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoriteFragmentViewModel @Inject constructor(private val beerRepository: BeerRepository) : BaseViewModel() {

    val favoriteList = beerRepository.favoriteBeers.stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    val emptyFavoriteVisibility: StateFlow<Int> = favoriteList
        .map { if (it.isEmpty()) View.VISIBLE else View.GONE }.stateIn(viewModelScope, SharingStarted.Lazily, View.GONE)

    suspend fun getIfExist(id: Int): Beer? {
        return beerRepository.returnIfExist(id)
    }

    fun updateFavorite(beer: Beer) {
        viewModelScope.launch {
            val updateResult = beerRepository.updateFavorite(beer)
            Timber.d("Update result: $updateResult")
        }
    }

    fun deleteFavorite(beer: Beer) {
        viewModelScope.launch {
            val updateResult = beerRepository.deleteFavorite(beer)
            Timber.d("Delete result: $updateResult")
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
        }
    }
}