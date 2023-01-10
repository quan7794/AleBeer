package app.wac.team.wacbase.ext

import app.wac.team.wacbase.base.UIState

sealed class ResultState<out F, out S> : UIState {
    data class Fail<out F>(val f: F) : ResultState<F, Nothing>()
    data class Success<out S>(val s: S) : ResultState<Nothing, S>()
}