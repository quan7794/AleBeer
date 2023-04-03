package app.interview.ale.base.ext

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.gson.Gson
import com.google.gson.JsonElement
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.time.ZoneOffset
import java.util.*
import java.util.concurrent.TimeUnit

fun Context.showToast(message: String?, length: Int = Toast.LENGTH_SHORT) {
    if (message == null) return else Toast.makeText(this, message, length).show()
}

inline fun <reified T> Gson.fromJson(jsonElement: JsonElement): T? = this.fromJson<T>(jsonElement, object : com.google.gson.reflect.TypeToken<T>() {}.type)

inline fun <reified T> Gson.fromJson(jsonStr: String?): T? = this.fromJson<T>(jsonStr, object : com.google.gson.reflect.TypeToken<T>() {}.type)

inline fun <reified T> Gson.ToJson(data: T?): String? = this.toJson(data, object : com.google.gson.reflect.TypeToken<T>() {}.type)

inline fun <reified T> String?.toObject(): T? {
    return if (isNullOrEmpty() || this == "[]") null
    else return Gson().fromJson<T>(this)
}

fun <T, K, R> LiveData<T>.combineWith(
    liveData: LiveData<K>,
    block: (T?, K?) -> R,
): LiveData<R> {
    val result = MediatorLiveData<R>()
    result.addSource(this) {
        result.value = block(this.value, liveData.value)
    }
    result.addSource(liveData) {
        result.value = block(this.value, liveData.value)
    }
    return result
}

fun Long.toFormattedTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val seconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

@SuppressLint("SimpleDateFormat")
fun Long.toDateTimeLeftFormat(): String {
    val dateFormat = SimpleDateFormat("dd'd' HH:mm:ss")
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = Date(this * 1000)

    val days = TimeUnit.MILLISECONDS.toDays(date.time).toInt()
    val remainingTime = date.time - TimeUnit.DAYS.toMillis(days.toLong())

    val hours = TimeUnit.MILLISECONDS.toHours(remainingTime).toInt()
    val remainingTimeMinutes = remainingTime - TimeUnit.HOURS.toMillis(hours.toLong())

    val minutes = TimeUnit.MILLISECONDS.toMinutes(remainingTimeMinutes).toInt()
    val remainingTimeSeconds = remainingTimeMinutes - TimeUnit.MINUTES.toMillis(minutes.toLong())

    val seconds = TimeUnit.MILLISECONDS.toSeconds(remainingTimeSeconds).toInt()

    return String.format("%dd %02d:%02d:%02d", days, hours, minutes, seconds)
}