package app.interview.ale.beer.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import mva3.adapter.ItemViewHolder
import mva3.adapter.MultiViewAdapter

class AleBeerAdapter : MultiViewAdapter() {

//    override fun onViewRecycled(holder: ItemViewHolder<*>) {
//        super.onViewRecycled(holder)
//        val position = holder.bindingAdapterPosition
//        if (position != RecyclerView.NO_POSITION) {
//            if (holder is BeerItemBinder.BeerViewHolder) holder.enableListener(false)
//            if (holder is FavoriteItemBinder.FavoriteViewHolder) holder.enableListener(false)
//        }
//    }

    override fun onViewAttachedToWindow(holder: ItemViewHolder<*>) {
        super.onViewAttachedToWindow(holder)
        if (holder is BeerItemBinder.BeerViewHolder) holder.enableListener()
        if (holder is FavoriteItemBinder.FavoriteViewHolder) holder.enableListener()
    }

    override fun onViewDetachedFromWindow(holder: ItemViewHolder<*>) {
        super.onViewDetachedFromWindow(holder)
        if (holder is BeerItemBinder.BeerViewHolder) holder.enableListener(false)
        if (holder is FavoriteItemBinder.FavoriteViewHolder) holder.enableListener(false)
    }
}