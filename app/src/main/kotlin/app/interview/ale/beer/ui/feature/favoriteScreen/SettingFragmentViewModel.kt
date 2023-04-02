package app.interview.ale.beer.ui.feature.favoriteScreen

import android.view.View
import app.interview.ale.base.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingFragmentViewModel @Inject constructor(private val dataStore: app.interview.ale.beer.data.local.datastore.DataStoreManager) : BaseViewModel() {

    override fun onClick(view: View) {
        when (view.id) {
            else -> {}
        }
    }
}