package app.wac.team.wacbase.ui.feature.firstScreen

import androidx.fragment.app.viewModels
import app.wac.team.wacbase.R
import app.wac.team.wacbase.base.BaseVmDbFragment
import app.wac.team.wacbase.databinding.FragmentFirstScreenBinding

class FirstScreenFragment : BaseVmDbFragment<FirstScreenViewModel, FragmentFirstScreenBinding>() {

    override fun getLayoutId() = R.layout.fragment_first_screen
    override val viewModel: FirstScreenViewModel by viewModels()

}