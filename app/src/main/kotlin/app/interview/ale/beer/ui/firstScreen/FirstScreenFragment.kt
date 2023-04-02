package app.interview.ale.beer.ui.firstScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import app.interview.ale.base.common.BaseVmDbFragment
import app.interview.ale.beer.R
import app.interview.ale.beer.databinding.FragmentFirstScreenBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstScreenFragment : BaseVmDbFragment<FirstScreenViewModel, FragmentFirstScreenBinding>() {
    private val firstScreenTabItems = arrayListOf(R.string.beer, R.string.favorite)

    override fun getLayoutId() = R.layout.fragment_first_screen
    override val viewModel: FirstScreenViewModel by viewModels()

    override fun setUpViews(savedInstanceState: Bundle?) {
        super.setUpViews(savedInstanceState)
        binding.apply {
            viewPager.adapter = ViewPagerAdapter(this@FirstScreenFragment)
            viewPager.isUserInputEnabled = false
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(firstScreenTabItems[position])
            }.attach()
        }
    }

    override fun onDestroyView() {
        binding.viewPager.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {}
            override fun onViewDetachedFromWindow(v: View) {
                binding.viewPager.adapter = null
            }
        })
        super.onDestroyView()
    }
}