package app.interview.ale.beer.ui.feature.favoriteScreen

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import app.interview.ale.base.common.BaseVmDbFragment
import app.interview.ale.base.ext.observe
import app.interview.ale.beer.R
import app.interview.ale.beer.databinding.FragmentFavoriteBinding
import app.interview.ale.beer.domain.entities.Beer
import app.interview.ale.beer.ui.adapter.AleBeerAdapter
import app.interview.ale.beer.ui.adapter.BeerItemBinder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mva3.adapter.ListSection
import mva3.adapter.util.InfiniteLoadingHelper
import mva3.adapter.util.Mode
import timber.log.Timber


@AndroidEntryPoint
class FavoriteFragment : BaseVmDbFragment<FavoriteFragmentViewModel, FragmentFavoriteBinding>() {
    override fun getLayoutId() = R.layout.fragment_favorite

    override val viewModel: FavoriteFragmentViewModel by viewModels()

    private var beerAdapter: AleBeerAdapter? = null
    private val beerSection = ListSection<Beer>()
    private val infiniteLoadingHelper: InfiniteLoadingHelper by lazy {
        object : InfiniteLoadingHelper(binding.beerList, R.layout.item_loading_footer, Int.MAX_VALUE) {
            override fun onLoadNextPage(page: Int) {
                Timber.d("Start Fetch")
                binding.beerList.adapter?.itemCount?.let { binding.beerList.smoothScrollToPosition(it) }
            }
        }
    }

    override fun setUpViews(savedInstanceState: Bundle?) {
        super.setUpViews(savedInstanceState)
        binding.vm = viewModel
        initBeerList()
//        binding.refresher.isEnabled = false
    }

    private fun initBeerList() {
        if (beerAdapter == null) beerAdapter = AleBeerAdapter()
        beerAdapter?.apply {
            registerItemBinders(BeerItemBinder { position, note ->
                showToast("Add to position $position, with note: $note")
            })
            addSection(beerSection)
            setSelectionMode(Mode.SINGLE)
            setInfiniteLoadingHelper(infiniteLoadingHelper)
        }
        binding.beerList.apply {
            adapter = beerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        infiniteLoadingHelper.markCurrentPageLoaded()
    }

    override fun setUpObservers() {
        super.setUpObservers()
        lifecycleScope.launch {
            viewModel.favoriteList.collect { beers ->
                beerSection.clear()
                beerSection.addAll(beers).also { Timber.d("Added to screen: $beers") }
                infiniteLoadingHelper.markCurrentPageLoaded()
            }
        }

        observe(viewModel.uiSingleEvent) { event ->
            when (event) {
                else -> showToast(getString(R.string.unknow_event))
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
            infiniteLoadingHelper.markCurrentPageLoaded()
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