package app.wac.team.base.common.header

import app.wac.team.base.common.UIState

sealed class HeaderState : UIState {
    object BackPress : HeaderState()
    object TitlePress : HeaderState()
    object MenuPress : HeaderState()
}