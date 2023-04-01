package app.wac.team.base.event.activity

import app.wac.team.base.common.BaseViewModel

class UpdateMainUIViewModel : BaseViewModel() {

    fun hideHeaderView(isHide: Boolean = true) {
        _uiState.value = MainUIState.HideHeaderView(isHide)
    }
}