package app.interview.ale.base.common

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import app.interview.ale.base.event.SingleLiveEvent
import app.interview.ale.base.ext.getTagName
import app.interview.ale.base.ext.logD
import app.interview.ale.base.ext.observe
import app.interview.ale.beer.basemodule.R
import timber.log.Timber

abstract class BaseVmFragment<VM : ViewModel> : Fragment() {

    abstract val viewModel: VM
    protected val TAG: String by getTagName()

    private var mRootView: View? = null
    protected var currentOrientation = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Fragment>.onCreate(savedInstanceState)
        logD("onCreate", "Entry")
        getFragmentArguments()
        initOnCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Timber.tag("BaseVmFragment").d("onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logD("onViewCreated", "ENTRY")
        setBindingVariables()
        setUpViews(savedInstanceState)
        setUpObservers()
        setUpData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    open fun getFragmentArguments() {}

    open fun initOnCreate(savedInstanceState: Bundle?) {}

    open fun setBindingVariables() {}

    open fun setUpViews(savedInstanceState: Bundle?) {}

    open fun setUpData() {}

    open fun setUpObservers() {
        val isShowCommonProgressBar: SingleLiveEvent<Boolean> = if (viewModel is BaseViewModel) (viewModel as BaseViewModel).showCommonProgressBar
        else (viewModel as BaseAndroidViewModel).showCommonProgressBar
        observe(isShowCommonProgressBar) {
            view?.findViewById<View>(R.id.commonProgressBar)?.apply {
                visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    protected fun showToast(message: String?, length: Int = Toast.LENGTH_SHORT) {
        if (message == null) return
        context?.let { Toast.makeText(it, message, length).show() }
    }

    open fun configurationChanged() {
        logD("configurationChange", "Entry")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (currentOrientation != newConfig.orientation) {
            currentOrientation = newConfig.orientation
            configurationChanged()
        }
    }
}