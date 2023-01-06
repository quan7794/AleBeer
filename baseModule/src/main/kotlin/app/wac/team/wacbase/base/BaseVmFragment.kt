package app.wac.team.wacbase.base

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import app.wac.team.wacbase.event.SingleLiveEvent
import app.wac.team.wacbase.ext.getTagName
import app.wac.team.wacbase.ext.injectObject
import app.wac.team.wacbase.ext.logD
import app.wac.team.wacbase.ext.observe
import com.example.basemodule.R

abstract class BaseVmFragment<VM : ViewModel> : Fragment() {

    abstract val viewModel: VM
    protected val TAG: String by getTagName()

    val logger: ILogger by injectObject()
    private var mRootView: View? = null
    protected var currentOrientation = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD("onCreate", "Entry")
        getFragmentArguments()
        initOnCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        logD("onCreateView")
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

    protected fun showToast(message: String?) {
        if (message == null) return
        context?.let { Toast.makeText(it, message, Toast.LENGTH_SHORT).show() }
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