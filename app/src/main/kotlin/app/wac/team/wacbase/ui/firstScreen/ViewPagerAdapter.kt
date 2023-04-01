package app.wac.team.wacbase.ui.firstScreen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.wac.team.wacbase.ui.feature.favoriteScreen.FavoriteFragment
import app.wac.team.wacbase.ui.feature.productScreen.ProductFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProductFragment()
            1 -> FavoriteFragment()
            else -> Fragment()
        }
    }
}