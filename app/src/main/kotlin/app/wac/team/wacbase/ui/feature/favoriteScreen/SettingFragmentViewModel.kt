package app.wac.team.wacbase.ui.feature.favoriteScreen

import android.view.View
import app.wac.team.base.common.BaseViewModel
import app.wac.team.wacbase.data.local.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingFragmentViewModel @Inject constructor(private val dataStore: DataStoreManager) : BaseViewModel() {

    override fun onClick(view: View) {
        when (view.id) {
            else -> {}
        }
    }
}