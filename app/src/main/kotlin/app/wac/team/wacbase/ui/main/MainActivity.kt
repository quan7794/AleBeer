package app.wac.team.wacbase.ui.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import app.wac.team.wacbase.R
import app.wac.team.wacbase.data.local.datastore.DataStoreManager
import app.wac.team.wacbase.domain.allowReads
import app.wac.team.wacbase.base.preference.AppSettings
import app.wac.team.wacbase.databinding.ActivityMainBinding
import app.wac.team.wacbase.di.module.navigationModule.AppNavigator
import app.wac.team.wacbase.util.extension.collectIn
import app.wac.team.wacbase.util.extension.setOnReactiveClickListener
import app.wac.team.wacbase.util.extension.viewInflateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewInflateBinding(ActivityMainBinding::inflate)

    @Inject
    lateinit var navigator: AppNavigator

    @Inject
    lateinit var dataStoreManager: DataStoreManager

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
            dataStoreManager.getSettingStream(AppSettings.NIGHT_MODE).collectIn(this@MainActivity) { mode ->
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