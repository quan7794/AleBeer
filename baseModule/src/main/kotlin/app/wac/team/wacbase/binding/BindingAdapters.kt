package app.wac.team.wacbase.binding

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import app.wac.team.wacbase.utils.ViewUtils.Companion.getDynamicDialogWidth
import app.wac.team.wacbase.utils.ViewUtils.Companion.getDynamicViewPadding
import com.example.basemodule.R
import timber.log.Timber

class BindingAdapters {

    companion object {
        val TAG: String = BindingAdapters::class.java.simpleName.toString()

        @JvmStatic
        @BindingAdapter("showView")
        fun showView(view: View, visible: Boolean? = null) {
            view.visibility = if (visible != null && visible) View.VISIBLE else View.GONE
        }

        @BindingAdapter("drawable")
        fun setImageDrawable(view: ImageView, drawable: Drawable?) {
            view.setImageDrawable(drawable)
        }

        @JvmStatic
        @BindingAdapter("selected")
        fun setSelected(view: View, isSelected: Boolean) {
            view.isSelected = isSelected
        }

        @JvmStatic
        @BindingAdapter("dynamicDialogWidth")
        fun dynamicDialogWidth(view: View, isDynamic: Boolean) {
            Timber.d("dynamicDialogWidth", "Init dynamic binding: $isDynamic")
            if (isDynamic) {
                val layoutParams = view.layoutParams
                layoutParams.width = getDynamicDialogWidth()
                Timber.d("dynamicDialogWidth", "new width: ${layoutParams.width}")
                view.layoutParams = layoutParams
            }
        }

        @JvmStatic
        @BindingAdapter("dynamicViewWidth")
        fun View.dynamicViewWidth(isDynamic: Boolean) {
            Timber.d("dynamicViewWidth", "Init dynamic binding: $isDynamic")
            if (!(this.context as Activity).isInMultiWindowMode) {
                if (isDynamic) {
                    val padding = getDynamicViewPadding()
                    Timber.d("dynamicViewWidth", "padding: $padding")
                    this.setPadding(padding, 0, padding, 0)
                }
            } else this.context.resources.getDimension(R.dimen.dimen_14dp_w).toInt().let { this.setPadding(it, 0, it, 0) }
        }
    }
}