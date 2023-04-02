package app.interview.ale.base.event.activity

import app.interview.ale.base.common.UIState

class MainUIState {
    class HideHeaderView(val isHide: Boolean = false) : UIState
}