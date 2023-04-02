package app.interview.ale.beer.ui.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import app.interview.ale.beer.databinding.ActivityMainBinding
import app.interview.ale.beer.di.module.navigationModule.AppNavigator
import app.interview.ale.beer.util.extension.collectIn
import app.interview.ale.beer.util.extension.viewInflateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewInflateBinding(ActivityMainBinding::inflate)

    @Inject
    lateinit var navigator: AppNavigator

    @Inject
    lateinit var dataStoreManager: app.interview.ale.beer.data.local.datastore.DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUI()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    override fun onSupportNavigateUp() = navigator.getNavController().navigateUp()

    override fun onDestroy() {
        if (isTaskRoot && isFinishing) {
            finishAfterTransition()
        }
        super.onDestroy()
    }

    private fun setupUI() {
        lifecycleScope.launch {
            dataStoreManager.getSettingStream(app.interview.ale.beer.base.preference.AppSettings.NIGHT_MODE).collectIn(this@MainActivity) { mode ->
                if (mode != null) {
                    setNightMode(mode)
                }
            }
        }
    }

    private fun setNightMode(mode: Int) {
        if (AppCompatDelegate.getDefaultNightMode() != mode) AppCompatDelegate.setDefaultNightMode(mode)
    }

}