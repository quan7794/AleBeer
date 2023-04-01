package app.wac.team.wacbase.base.preference

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object AppSettings {

    //SYSTEM_SETTING
    val OPEN_AI_API = stringPreferencesKey("USER_API")
    val OPEN_AI_API_ACTIVE = booleanPreferencesKey("USER_API_ACTIVE")

    //GLOBAL_SETTING
    val NIGHT_MODE = intPreferencesKey("night_mode")
    val MODE_NIGHT_DEFAULT = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM else AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY

    //AI_CHAT_SETTING
    val AICHAT_AUTO_SEND_AFTER_VOICE = booleanPreferencesKey("aichat_auto_send_after_voice")
    val AICHAT_MEMORY_ABILITY = booleanPreferencesKey("aichat_memory_ability")
    val AICHAT_SMART_LEVEL = intPreferencesKey("aichat_smart_level")
    val AICHAT_BACKGROUND_INDEX = intPreferencesKey("aichat_background_index")

    fun chatSettingList() = listOf(AICHAT_AUTO_SEND_AFTER_VOICE, AICHAT_MEMORY_ABILITY)
}