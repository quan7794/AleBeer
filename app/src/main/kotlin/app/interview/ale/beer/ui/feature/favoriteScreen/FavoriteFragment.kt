package app.interview.ale.beer.ui.feature.favoriteScreen

import android.os.Bundle
import androidx.fragment.app.viewModels
import app.interview.ale.base.common.BaseVmDbFragment
import app.interview.ale.base.ext.observe
import app.interview.ale.beer.R
import app.interview.ale.beer.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : BaseVmDbFragment<SettingFragmentViewModel, FragmentFavoriteBinding>() {
    override fun getLayoutId() = R.layout.fragment_favorite

    @Inject
    lateinit var dataStore: app.interview.ale.beer.data.local.datastore.DataStoreManager

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