package app.wac.team.wacbase.util.extension
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import app.wac.team.wacbase.base.preference.AppSettings
import kotlin.math.abs

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

inline fun <T : ViewBinding> AppCompatActivity.viewInflateBinding(crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun Int.mapToNearestNumber(setOfNumbers: Set<Int>): Int {
    var nearestNumber = 0
    var smallestDifference = Int.MAX_VALUE

    for (number in setOfNumbers) {
        val difference = abs(this - number)
        if (difference < smallestDifference) {
            smallestDifference = difference
            nearestNumber = number
        }
    }
    return nearestNumber
}


fun Int.mapNightModeToSeekBar() = when (this) {
    AppCompatDelegate.MODE_NIGHT_YES -> 0
    AppCompatDelegate.MODE_NIGHT_NO -> 1
    else -> 2
}

fun Int.mapSeekbarToNightMode() = when (this) {
    0 -> AppCompatDelegate.MODE_NIGHT_YES
    1 -> AppCompatDelegate.MODE_NIGHT_NO
    else -> AppSettings.MODE_NIGHT_DEFAULT
}

fun Context.showKeyboard(view: View) {
    this.getSystemService(Context.INPUT_METHOD_SERVICE)?.let { imm ->
        (imm as InputMethodManager).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun EditText.onDone(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback.invoke()
            return@setOnEditorActionListener true
        }
        false
    }
}
