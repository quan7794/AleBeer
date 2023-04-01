package app.wac.team.base.common.header

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.wac.team.base.common.BaseViewModel
import app.wac.team.wacbase.basemodule.R

open class HeaderViewModel() : BaseViewModel() {

    open fun onHeaderClick(view: View) {
        when (view.id) {
            R.id.hdBtnBack -> uiSingleEvent.postValue(HeaderState.BackPress)
            R.id.hdTitle -> uiSingleEvent.postValue(HeaderState.TitlePress)
            R.id.hdBtnMenu -> uiSingleEvent.postValue(HeaderState.MenuPress)
        }
    }

    private val _moreButtonVisibility = MutableLiveData(View.GONE)
    val moreButtonVisibility: LiveData<Int>
        get() = _moreButtonVisibility

    fun showMoreButton(isShow: Boolean) {
        _moreButtonVisibility.postValue(if (isShow) View.VISIBLE else View.GONE)
    }

    open fun getHeaderTitle(context: Context): String = ""

    open fun getDescription(context: Context): String = ""
}