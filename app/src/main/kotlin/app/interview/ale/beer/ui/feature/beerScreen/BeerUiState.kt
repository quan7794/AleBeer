package app.interview.ale.beer.ui.feature.beerScreen

import app.interview.ale.base.common.UIState

sealed class BeerUiState : UIState {
    class FetchError(val message: String) : BeerUiState()
}
