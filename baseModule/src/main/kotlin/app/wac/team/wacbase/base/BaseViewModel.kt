package app.wac.team.wacbase.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wac.team.wacbase.event.SingleLiveEvent
import app.wac.team.wacbase.ext.injectObject
import app.wac.team.wacbase.ext.logD
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val mCompositeDisposable by lazy { CompositeDisposable() }
    val showCommonProgressBar: SingleLiveEvent<Boolean> by lazy { SingleLiveEvent() }
    val uiSingleEvent: SingleLiveEvent<UIState> by lazy { SingleLiveEvent() }

    var errorMessage = SingleLiveEvent<String>()
    var exceptionError: AppException? = null

    protected val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> get() = _uiState

    protected val _clickEvent = SingleLiveEvent<UIState>()
    val clickEvent: LiveData<UIState> get() = _clickEvent

    val logger: ILogger by injectObject()
    val handlerCoroutineException = CoroutineExceptionHandler { _, exception -> manageCoroutineException(exception) }

    open fun manageCoroutineException(exception: Any) {
        viewModelScope.launch {
            if (exception is AppException) errorMessage.value = exception.message
            else if (exception is Throwable) {
                errorMessage.value = exception.localizedMessage
                logD("manageCoroutineException", exception.localizedMessage)
                customCoroutineException(exception)
            }
            showCommonProgressBar.value = false
        }
    }

    protected open fun customCoroutineException(exception: Any) {
    }

    open fun manageException(exception: Any) {
        showCommonProgressBar.value = false
    }

    open fun addSubscribe(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        logD("onCleared", "Enter")
        mCompositeDisposable.clear()
    }

    fun clearDispose() = mCompositeDisposable.clear()

    open fun onDestroyView() {}

}