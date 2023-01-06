package app.wac.team.wacbase.ext

import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter

fun View.gone() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

@BindingAdapter("active")
fun View.active(value: Boolean = true) {
    isEnabled = value
    isClickable = value
    alpha = if (value) 1f else 0.3f
}

infix fun View.onClick(func: () -> Unit) {
    this.setOnClickListener { func.invoke() }
}

@BindingAdapter("enableView")
fun View.enableView(value: Boolean = true) {
    isEnabled = value
    alpha = if (value) 1f else 0.4f
}

@BindingAdapter("layoutHeight")
fun View.setLayoutHeight(dimen: Float) {
    val newLayoutParams = layoutParams
    newLayoutParams.height = dimen.toInt()
    layoutParams = newLayoutParams
}

@BindingAdapter("layoutMarginTop")
fun View.setLayoutMarginTop(marginTop: Float) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val newParams = layoutParams as ViewGroup.MarginLayoutParams
        newParams.setMargins(newParams.marginStart, marginTop.toInt(), newParams.rightMargin, newParams.bottomMargin)
        layoutParams = newParams
    }
}