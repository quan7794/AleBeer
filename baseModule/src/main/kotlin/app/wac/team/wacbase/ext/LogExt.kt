package app.wac.team.wacbase.ext

import app.wac.team.wacbase.base.BaseViewModel
import app.wac.team.wacbase.base.BaseVmFragment
import timber.log.Timber

fun BaseVmFragment<*>.logD(func: String, msg: String = "Entry") {
    Timber.d(this::class.java.simpleName, func, msg)
}

fun BaseVmFragment<*>.logV(func: String, msg: String = "Entry") {
    Timber.v(this::class.java.simpleName, func, msg)
}


fun BaseViewModel.logD(func: String, msg: String = "Entry") {
    Timber.d(this::class.java.simpleName, func, msg)
}

fun BaseViewModel.logW(func: String, msg: String = "Entry") {
    Timber.w(this::class.java.simpleName, func, msg)
}

fun BaseViewModel.logE(func: String, msg: String = "Entry") {
    Timber.e(this::class.java.simpleName, func, msg)
}

fun BaseVmFragment<*>.logE(func: String, msg: String = "Entry") {
    Timber.e(this::class.java.simpleName, func, msg)
}

fun BaseVmFragment<*>.logI(func: String, msg: String = "Entry") {
    Timber.i(this::class.java.simpleName, func, msg)
}

fun BaseViewModel.logI(func: String, msg: String = "Entry") {
    Timber.i(this::class.java.simpleName, func, msg)
}

fun BaseVmFragment<*>.logW(func: String, msg: String = "Entry") {
    Timber.w(this::class.java.simpleName, func, msg)
}