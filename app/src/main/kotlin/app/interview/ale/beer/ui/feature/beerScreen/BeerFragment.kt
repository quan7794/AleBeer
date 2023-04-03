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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import mva3.adapter.ListSection
import mva3.adapter.MultiViewAdapter
import mva3.adapter.util.InfiniteLoadingHelper
import timber.log.Timber


@Suppress("RemoveExplicitTypeArguments")
@AndroidEntryPoint
class BeerFragment : BaseVmDbFragment<BeerFragmentViewModel, FragmentBeerBinding>() {
    override fun getLayoutId() = R.layout.fragment_beer
//
//    @Inject
//    lateinit var navigator: AppNavigator

    override val viewModel: BeerFragmentViewModel by viewModels()

    private var beerAdapter: MultiViewAdapter? = null
    private val beerSection = ListSection<Beer>()
    private val infiniteLoadingHelper: InfiniteLoadingHelper by lazy {
        object : InfiniteLoadingHelper(binding.beerList, R.layout.item_loading_footer, Int.MAX_VALUE) {
            override fun onLoadNextPage(page: Int) {
                lifecycleScope.launch {
                    delay(2000)
                    viewModel.fetchPage()
                }
            }
        }
    }

    override fun setUpViews(savedInstanceState: Bundle?) {
        super.setUpViews(savedInstanceState)
        binding.vm = viewModel
        initBeerList()
        initSwipeAction()
    }

    private fun addToFavorite(position: Int, note: String) {
        showToast("Saving to Room")
        val favoriteBeer = beerSection.get(position).copy(note = note)
        viewModel.addToLocalDb(favoriteBeer)
        beerSection.set(position, favoriteBeer)
    }

    private fun initBeerList() {
        if (beerAdapter == null) beerAdapter = MultiViewAdapter()
        beerAdapter?.apply {
            registerItemBinders(BeerItemBinder { position, note ->
                showToast("Add to position $position, with note: $note")
                addToFavorite(position, note)
            })
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
            }
        }
    }

    override fun setUpObservers() {
        super.setUpObservers()
        lifecycleScope.launch {
            viewModel.currentPage.collect { page ->
                Timber.d("Got beer: ${page?.beers}")
                page?.beers?.let { beers ->
                    beers.asFlow().map {
                        val cache = viewModel.getIfExist(it.id)
                        if (cache != null) it.copy(note = cache.note) else it
                    }.collect { beer -> beerSection.add(beer).also { Timber.d("Add to screen: $beer") } }

                    if (viewModel.pageIndex.value == 1) binding.beerList.scrollToPosition(0)
                    delay(300)
                    infiniteLoadingHelper.markCurrentPageLoaded()
                }
            }
        }
        observe(viewModel.uiSingleEvent) { event ->
            when (event) {
                is BeerUiState.FetchError -> infiniteLoadingHelper.markAllPagesLoaded().also { showToast(event.message) }
                else -> showToast(getString(R.string.unknow_event))
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