package app.interview.ale.beer.ui.feature.beerScreen

import mva3.adapter.ItemViewHolder
import mva3.adapter.MultiViewAdapter

class AleBeerAdapter : MultiViewAdapter() {
    val textHolder: MutableMap<Int, String> = mutableMapOf()

    override fun onViewAttachedToWindow(holder: ItemViewHolder<*>) {
        super.onViewAttachedToWindow(holder)
        if (holder is BeerItemBinder.BeerViewHolder) holder.enableTextWatcher()
    }

    override fun onViewDetachedFromWindow(holder: ItemViewHolder<*>) {
        super.onViewDetachedFromWindow(holder)
        if (holder is BeerItemBinder.BeerViewHolder) holder.enableTextWatcher(false)
    }
}