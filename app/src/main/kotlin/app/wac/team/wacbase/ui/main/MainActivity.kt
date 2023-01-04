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
import app.wac.team.wacbase.base.preference.Settings
import app.wac.team.wacbase.databinding.ActivityMainBinding
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
    private val navController: NavController by lazy {
        findNavController(R.id.activityMainChooseHostFragment)
    }
    private var uiStateJob: Job? = null

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

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onStop() {
        uiStateJob?.cancel()
        super.onStop()
    }

    override fun onDestroy() {
        if (isTaskRoot && isFinishing) {
            finishAfterTransition()
        }
        super.onDestroy()
    }

    private fun setupUI() {
        lifecycleScope.launch {
            dataStoreManager.themeMode.collectIn(this@MainActivity) { mode ->
                setNightMode(mode)
            }
        }
    }

    private fun setNightMode(mode: Int) {
        allowReads {
            uiStateJob = lifecycleScope.launchWhenStarted {
                dataStoreManager.setThemeMode(mode)
            }
        }
        when (mode) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.activityMainSwitchThemeFab.setImageResource(R.drawable.ic_launcher_foreground)
                binding.activityMainSwitchThemeFab.setOnReactiveClickListener {
                    setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.activityMainSwitchThemeFab.setImageResource(R.drawable.ic_launcher_background)
                binding.activityMainSwitchThemeFab.setOnReactiveClickListener {
                    setNightMode(Settings.MODE_NIGHT_DEFAULT)
                }
            }
            else -> {
                binding.activityMainSwitchThemeFab.setImageResource(R.color.purple_500)
                binding.activityMainSwitchThemeFab.setOnReactiveClickListener {
                    setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
        if (AppCompatDelegate.getDefaultNightMode() != mode)
            AppCompatDelegate.setDefaultNightMode(mode)
    }

}