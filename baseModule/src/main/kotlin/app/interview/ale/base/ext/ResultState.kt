package app.interview.ale.base.ext

import app.interview.ale.base.common.UIState

sealed class ResultState<out F, out S> : UIState {
    data class Fail<out F>(val f: F) : ResultState<F, Nothing>()
    data class Success<out S>(val s: S) : ResultState<Nothing, S>()
}