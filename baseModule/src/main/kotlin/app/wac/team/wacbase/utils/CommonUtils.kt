package app.wac.team.wacbase.utils

import android.content.Context
import android.net.wifi.WifiManager

object CommonUtils {

    fun connectedWifiName(context: Context): String? {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo
        var ssid = wifiInfo?.ssid
        ssid = ssid?.trimStart('"')?.trimEnd('"')
        return ssid
    }
}