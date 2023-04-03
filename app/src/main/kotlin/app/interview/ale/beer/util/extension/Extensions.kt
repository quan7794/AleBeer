package app.interview.ale.beer.util.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import app.interview.ale.base.ext.enableEdit
import app.interview.ale.base.ext.gone
import app.interview.ale.base.ext.show
import app.interview.ale.beer.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*
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
    else -> app.interview.ale.beer.base.preference.AppSettings.MODE_NIGHT_DEFAULT
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


fun ImageView.loadImage(imageUrl: String) {
    val requestBuilder = Glide.with(context)
        .asDrawable()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.image_place_holder)
        .load(imageUrl)
    requestBuilder.preload()
    requestBuilder.into(this)
}

fun TextInputEditText.refreshBeerNote(note: String, textWatcherData: String? = null) {
    val isNoted = note.isNotEmpty()
    if (textWatcherData == null) setText(note.ifEmpty { "" })
    hint = if (isNoted) "" else resources.getText(R.string.note_hint)
    enableEdit(isNoted.not())
}

fun TextInputEditText.refreshFavoriteNote(note: String, textHolderData: String?) {
    textHolderData?.let { setText(it) } ?: run { setText(note) }
}

fun MaterialButton.refreshBeerNote(note: String) {
    text = resources.getText(R.string.save)
    if (note.isNotEmpty()) gone() else show()
}

fun MaterialTextView.setFormattedDate(msTime: Long, format: String = "HH:mm:ss dd-MM") {
    val date = Date(msTime)
    val formattedTime = SimpleDateFormat(format, Locale.getDefault()).format(date)
    text = context.getString(R.string.sale_off_in, formattedTime)
}
