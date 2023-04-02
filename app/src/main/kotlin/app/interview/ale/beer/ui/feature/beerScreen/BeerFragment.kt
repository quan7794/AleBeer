package app.interview.ale.beer.ui.feature.beerScreen

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import app.interview.ale.base.common.BaseVmDbFragment
import app.interview.ale.base.ext.observe
import app.interview.ale.beer.R
import app.interview.ale.beer.databinding.FragmentBeerBinding
import app.interview.ale.beer.di.module.navigationModule.AppNavigator
import app.interview.ale.beer.domain.entities.Beer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mva3.adapter.ListSection
import mva3.adapter.MultiViewAdapter
import mva3.adapter.util.InfiniteLoadingHelper
import timber.log.Timber
import javax.inject.Inject


@Suppress("RemoveExplicitTypeArguments")
@AndroidEntryPoint
class BeerFragment : BaseVmDbFragment<BeerFragmentViewModel, FragmentBeerBinding>() {
    override fun getLayoutId() = R.layout.fragment_beer

    @Inject
    lateinit var navigator: AppNavigator

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
        restoreLastState(savedInstanceState)
    }

    override fun setBindingVariables() {
        super.setBindingVariables()
        binding.refresher.setOnRefreshListener {
            lifecycleScope.launch {
                beerSection.clear()
                viewModel.fetchPage(toNextPage = false)
                binding.refresher.isRefreshing = false
            }
        }
    }

    private fun initBeerList() {
        if (beerAdapter == null) beerAdapter = MultiViewAdapter()
        beerAdapter?.apply {
            registerItemBinders(BeerItemBinder())
            addSection(beerSection)
            setInfiniteLoadingHelper(infiniteLoadingHelper)
        }
        binding.beerList.apply {
            adapter = beerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun restoreLastState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val currentBeerList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getParcelableArrayList(BEER_LIST, Beer::class.java)
            } else {
                savedInstanceState.getParcelableArrayList<Beer>(BEER_LIST)
            }
            currentBeerList?.let { beerSection.addAll(currentBeerList).also { infiniteLoadingHelper.markCurrentPageLoaded() } }
        }
    }

    override fun setUpObservers() {
        super.setUpObservers()
        lifecycleScope.launch {
            viewModel.currentPage.collect { page ->
                Timber.d("Got beer: ${page?.beers}")
                page?.beers?.let { beers ->
                    beerSection.addAll(beers)
                    if (viewModel.pageIndex.value == 1) binding.beerList.scrollToPosition(0)
                    delay(300)
                    infiniteLoadingHelper.markCurrentPageLoaded()
                }
            }
        }
        observe(viewModel.uiSingleEvent) {
            when (it) {
                else -> {}
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(BEER_LIST, ArrayList(beerSection.data))
    }

    override fun onDestroy() {
        super.onDestroy()
        beerAdapter = null
    }

    companion object {
        const val BEER_LIST = "beerList"
    }
}