package app.interview.ale.beer.ui.feature.favoriteScreen

import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import app.interview.ale.base.common.BaseVmDbFragment
import app.interview.ale.beer.R
import app.interview.ale.beer.databinding.FragmentFavoriteBinding
import app.interview.ale.beer.domain.entities.Beer
import app.interview.ale.beer.ui.adapter.AleBeerAdapter
import app.interview.ale.beer.ui.adapter.FavoriteItemBinder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mva3.adapter.ListSection
import timber.log.Timber


@AndroidEntryPoint
class FavoriteFragment : BaseVmDbFragment<FavoriteFragmentViewModel, FragmentFavoriteBinding>() {
    override fun getLayoutId() = R.layout.fragment_favorite

    override val viewModel: FavoriteFragmentViewModel by viewModels()

    private var beerAdapter: AleBeerAdapter? = null
    private val beerSection = ListSection<Beer>()

    override fun setUpViews(savedInstanceState: Bundle?) {
        super.setUpViews(savedInstanceState)
        binding.vm = viewModel
        initBeerList()
    }

    private fun initBeerList() {
        if (beerAdapter == null) beerAdapter = AleBeerAdapter()
        beerAdapter?.apply {
            registerItemBinders(
                FavoriteItemBinder(
                    onUpdateClick = { beerId, newNote ->
                        val originalBeer = beerSection.data.first { it.id == beerId }
                        if (originalBeer.note == newNote) showToast("No changes!")
                        else {
                            val updatedBeer = originalBeer.copy(note = newNote)
                            viewModel.updateFavorite(updatedBeer)
                            showToast("Updated ${updatedBeer.name}, new note: ${updatedBeer.note}")
                        }
                    },
                    onDeleteClick = { beerId ->
                        val beer = beerSection.data.first { it.id == beerId }
                        viewModel.deleteFavorite(beer)
                        showToast("Deleted ${beer.name}, with note ${beer.note}")
                    })
            )
            addSection(beerSection)
        }
        binding.beerList.apply {
            adapter = beerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun setUpObservers() {
        super.setUpObservers()
        lifecycleScope.launch {
            viewModel.favoriteList.collect { beers ->
                beerSection.clear()
                beerSection.addAll(beers).also { Timber.d("Added to screen: $beers") }
                beerAdapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(BEER_LIST_STATE_KEY, binding.beerList.layoutManager?.onSaveInstanceState())
    }

    @Suppress("DEPRECATION")
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            Timber.d("Entry")
            val listState = savedInstanceState.getParcelable<Parcelable>(BEER_LIST_STATE_KEY)
            binding.beerList.layoutManager?.onRestoreInstanceState(listState)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        beerAdapter = null
    }

    companion object {
        const val BEER_LIST_STATE_KEY = "beerListState"
    }
}