package app.interview.ale.beer.ui.feature.beerScreen

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import app.interview.ale.base.common.BaseVmDbFragment
import app.interview.ale.base.ext.observe
import app.interview.ale.beer.R
import app.interview.ale.beer.databinding.FragmentBeerBinding
import app.interview.ale.beer.domain.entities.Beer
import app.interview.ale.beer.ui.adapter.AleBeerAdapter
import app.interview.ale.beer.ui.adapter.BeerItemBinder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import mva3.adapter.ListSection
import mva3.adapter.util.InfiniteLoadingHelper
import timber.log.Timber


@Suppress("RemoveExplicitTypeArguments")
@AndroidEntryPoint
class BeerFragment : BaseVmDbFragment<BeerFragmentViewModel, FragmentBeerBinding>() {
    override fun getLayoutId() = R.layout.fragment_beer

//    @Inject
//    lateinit var navigator: AppNavigator

    override val viewModel: BeerFragmentViewModel by viewModels()

    private var beerAdapter: AleBeerAdapter? = null
    private val beerSection = ListSection<Beer>()
    private val beerItemBinder = BeerItemBinder { position, note -> addToFavorite(position, note) }
    private val infiniteLoadingHelper: InfiniteLoadingHelper by lazy {
        object : InfiniteLoadingHelper(binding.beerList, R.layout.item_loading_footer, Int.MAX_VALUE) {
            override fun onLoadNextPage(page: Int) {
                lifecycleScope.launch {
                    Timber.d("Start Fetch")
                    viewModel.fetchPage()
                }
            }
        }
    }

    override fun setUpViews(savedInstanceState: Bundle?) {
        super.setUpViews(savedInstanceState)
        initBeerList()
        initSwipeAction()
    }

    private fun addToFavorite(position: Int, note: String) {
        Timber.d("Saving to Room")
        val favoriteBeer = beerSection.get(position).copy(note = note)
        viewModel.addToLocalDb(favoriteBeer)
        showToast("Added to favorite: ${favoriteBeer.name}, $note")
        beerSection.set(position, favoriteBeer)
    }

    private fun initBeerList() {
        if (beerAdapter == null) beerAdapter = AleBeerAdapter()
        beerAdapter?.apply {
            registerItemBinders(beerItemBinder)
            addSection(beerSection)
            setInfiniteLoadingHelper(infiniteLoadingHelper)
        }
        binding.beerList.apply {
            adapter = beerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initSwipeAction() {
        binding.refresher.setOnRefreshListener {
            lifecycleScope.launch {
                beerSection.clear()
                viewModel.fetchPage(toNextPage = false)
                binding.refresher.isRefreshing = false
                infiniteLoadingHelper.markCurrentPageLoaded()
            }
        }
    }

    override fun setUpObservers() {
        super.setUpObservers()
        lifecycleScope.launch {
            viewModel.currentPage.collect { page ->
                Timber.d("Got beer: ${page?.beers}")
                page?.beers?.let { beers ->
                    addBeersAsync(beers).invokeOnCompletion {
                        Timber.d("Add beers completed")
                        if (viewModel.pageIndex.value == 1) binding.beerList.scrollToPosition(0)
                        infiniteLoadingHelper.markCurrentPageLoaded()
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.favoriteList.collect { favoriteBeers ->
                beerSection.data.forEachIndexed { index, beerListItem ->
                    val dataOnFavorite = favoriteBeers.firstOrNull { beerListItem.id == it.id }
                    beerItemBinder.textHolder[beerListItem.id] = dataOnFavorite?.note ?: ""
                    beerSection.set(index, dataOnFavorite ?: beerListItem.copy(note = ""))
                }
            }
        }
        observe(viewModel.uiSingleEvent) { event ->
            when (event) {
                is BeerUiState.FetchError -> infiniteLoadingHelper.markCurrentPageLoaded().also { showToast(event.message) }
                else -> showToast(getString(R.string.unknow_event))
            }
        }
    }

    private suspend fun addBeersAsync(beers: List<Beer>): Job {
        return lifecycleScope.launch {
            beers.asFlow().map {
                val cache = viewModel.getIfExist(it.id)
                if (cache != null) it.copy(note = cache.note) else it
            }.collect { beer ->
                beerSection.add(beer).also { Timber.d("Add to screen: $beer") }
                delay(100)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(BEER_LIST_KEY, ArrayList(beerSection.data))
        outState.putParcelable(BEER_LIST_STATE_KEY, binding.beerList.layoutManager?.onSaveInstanceState())
    }

    @Suppress("DEPRECATION")
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            Timber.d("Entry")
            val currentBeerList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                savedInstanceState.getParcelableArrayList(BEER_LIST_KEY, Beer::class.java)
            else savedInstanceState.getParcelableArrayList<Beer>(BEER_LIST_KEY)
            currentBeerList?.let { beerSection.addAll(currentBeerList).also { infiniteLoadingHelper.markCurrentPageLoaded() } }
            val listState = savedInstanceState.getParcelable<Parcelable>(BEER_LIST_STATE_KEY)
            binding.beerList.layoutManager?.onRestoreInstanceState(listState)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        beerAdapter = null
    }

    companion object {
        const val BEER_LIST_KEY = "beerList"
        const val BEER_LIST_STATE_KEY = "beerListState"
    }
}