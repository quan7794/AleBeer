package app.wac.team.wacbase.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


fun <T> Fragment.observe(liveData: LiveData<T>?, observer: (T) -> Unit) =
    liveData?.observe(viewLifecycleOwner, Observer(observer))

fun <T> FragmentActivity.observe(liveData: LiveData<T>?, observer: (T) -> Unit) =
    liveData?.observe(this, Observer(observer))



