package app.interview.ale.beer.ui.feature.beerScreen

import mva3.adapter.ItemViewHolder
import mva3.adapter.MultiViewAdapter

class AleBeerAdapter : MultiViewAdapter() {

    override fun onViewAttachedToWindow(holder: ItemViewHolder<*>) {
        super.onViewAttachedToWindow(holder)
        if (holder is BeerItemBinder.BeerViewHolder) holder.enableListener()
    }

    override fun onViewDetachedFromWindow(holder: ItemViewHolder<*>) {
        super.onViewDetachedFromWindow(holder)
        if (holder is BeerItemBinder.BeerViewHolder) holder.enableListener(false)
    }
}