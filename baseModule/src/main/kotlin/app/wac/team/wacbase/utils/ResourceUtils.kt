package app.wac.team.wacbase.utils

import androidx.fragment.app.Fragment

inline fun Fragment.getInteger(resId: Int): Int? = context?.resources?.getInteger(resId)

inline fun Fragment.getDimension(resId: Int): Int? = context?.resources?.getDimensionPixelSize(resId)

inline fun Fragment.getString(sId: Int) = if (sId == 0) "" else context?.getString(sId)

inline fun Fragment.getColor(resId: Int): Int? = context?.resources?.getColor(resId)
