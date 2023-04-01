package app.wac.team.base.ext

import app.wac.team.base.common.UIState

sealed class ResultState<out F, out S> : UIState {
    data class Fail<out F>(val f: F) : ResultState<F, Nothing>()
    data class Success<out S>(val s: S) : ResultState<Nothing, S>()
}