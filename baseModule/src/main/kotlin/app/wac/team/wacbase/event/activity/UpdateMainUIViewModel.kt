package app.wac.team.wacbase.event.activity

import app.wac.team.wacbase.base.BaseViewModel

class UpdateMainUIViewModel : BaseViewModel() {

    fun hideHeaderView(isHide: Boolean = true) {
        _uiState.value = MainUIState.HideHeaderView(isHide)
    }
}