package com.reubenk.wifitoggle

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.ComponentName
import android.service.quicksettings.TileService



class MyReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val componentName = ComponentName(p0!!, MyService::class.java)

        TileService.requestListeningState(p0!!, componentName)
    }
}