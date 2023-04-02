package app.interview.ale.beer.ui.firstScreen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.interview.ale.beer.ui.feature.favoriteScreen.FavoriteFragment
import app.interview.ale.beer.ui.feature.beerScreen.BeerFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BeerFragment()
            1 -> FavoriteFragment()
            else -> Fragment()
        }
    }
}