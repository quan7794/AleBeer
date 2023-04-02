package app.interview.ale.base.common.header

import app.interview.ale.base.common.UIState

sealed class HeaderState : UIState {
    object BackPress : HeaderState()
    object TitlePress : HeaderState()
    object MenuPress : HeaderState()
}