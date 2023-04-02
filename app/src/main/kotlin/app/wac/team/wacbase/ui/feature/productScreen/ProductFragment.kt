package app.wac.team.wacbase.ui.feature.productScreen

import android.os.Bundle
import androidx.fragment.app.viewModels
import app.wac.team.base.common.BaseVmDbFragment
import app.wac.team.base.ext.observe
import app.wac.team.wacbase.R
import app.wac.team.wacbase.databinding.FragmentProductBinding
import app.wac.team.wacbase.di.module.navigationModule.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductFragment : BaseVmDbFragment<ProductFragmentViewModel, FragmentProductBinding>() {
    override fun getLayoutId() = R.layout.fragment_product

    @Inject
    lateinit var navigator: AppNavigator

    override val viewModel: ProductFragmentViewModel by viewModels()

    override fun setUpViews(savedInstanceState: Bundle?) {
        super.setUpViews(savedInstanceState)
        binding.vm = viewModel
    }

    override fun setUpObservers() {
        super.setUpObservers()
        observe(viewModel.uiSingleEvent) {
            when (it) {
                else -> {}
            }
        }

    }
}