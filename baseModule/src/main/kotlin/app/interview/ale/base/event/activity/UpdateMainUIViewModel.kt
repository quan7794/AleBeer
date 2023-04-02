package app.interview.ale.base.event.activity

import app.interview.ale.base.common.BaseViewModel

class UpdateMainUIViewModel : BaseViewModel() {

    fun hideHeaderView(isHide: Boolean = true) {
        _uiState.value = MainUIState.HideHeaderView(isHide)
    }
}