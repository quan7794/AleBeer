package app.wac.team.wacbase.ui.feature.favoriteScreen

import android.os.Bundle
import androidx.fragment.app.viewModels
import app.wac.team.base.common.BaseVmDbFragment
import app.wac.team.base.ext.observe
import app.wac.team.wacbase.R
import app.wac.team.wacbase.data.local.datastore.DataStoreManager
import app.wac.team.wacbase.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : BaseVmDbFragment<SettingFragmentViewModel, FragmentFavoriteBinding>() {
    override fun getLayoutId() = R.layout.fragment_favorite

    @Inject
    lateinit var dataStore: DataStoreManager

    override val viewModel: SettingFragmentViewModel by viewModels()

    override fun setUpViews(savedInstanceState: Bundle?) {
        super.setUpViews(savedInstanceState)
        binding.vm = viewModel
    }

    override fun setUpObservers() {
        super.setUpObservers()
        observe(viewModel.uiSingleEvent) {
//            when (it) {
//            }
        }
    }
}