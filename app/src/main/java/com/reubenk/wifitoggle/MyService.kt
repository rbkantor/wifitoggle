package com.reubenk.wifitoggle

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.service.quicksettings.TileService
import android.service.quicksettings.Tile
import android.widget.Toast

import android.R
import android.content.Context

import android.provider.Settings.System.SCREEN_OFF_TIMEOUT
import android.net.wifi.WifiManager
import android.content.BroadcastReceiver
import android.util.Log
import android.content.IntentFilter





class MyService : TileService() {

    //override fun onBind(intent: Intent): IBinder {

    //}

    var filter: IntentFilter? = null

    //The BroadcastReceiver that listens for bluetooth broadcasts
    val myReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action.equals(
                    "android.net.wifi.STATE_CHANGE",
                    ignoreCase = true
                )
            ) {
                Log.d("log", "Brandon let's go")
                updateTile(null)
            }
        }
    }

    override fun onStartListening() {
        updateTile(null)
        super.onStartListening()

        filter = IntentFilter("android.net.wifi.STATE_CHANGE");
        registerReceiver(myReceiver, filter);
        //The BroadcastReceiver that listens for bluetooth broadcasts



    }

    override fun onStopListening() {
        updateTile(null)
        unregisterReceiver(myReceiver);
        super.onStopListening()
    }

    fun updateTile(override: Boolean?) {
        val tile = qsTile
        if (tile != null && override == null) {
            val wifiManager =
                this.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            if (wifiManager.isWifiEnabled()) {
                tile.state = Tile.STATE_ACTIVE
                tile.label = "wifi on"
            } else {
                tile.state = Tile.STATE_INACTIVE
                tile.label = "wifi off"
            }

            tile.updateTile()
        }
        if (tile != null && override != null) {

            if (override) {
                tile.state = Tile.STATE_ACTIVE
                tile.label = "wifi on"
            } else {
                tile.state = Tile.STATE_INACTIVE
                tile.label = "wifi off"
            }

            tile.updateTile()
        }
    }

    override fun onClick() {
        super.onClick()

        val wifiManager =
            this.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (wifiManager.isWifiEnabled()) {
            Toast.makeText(
                applicationContext,
                "wifi off",
                Toast.LENGTH_SHORT
            ).show()
            wifiManager.isWifiEnabled = false
            updateTile(false)
        } else {
            Toast.makeText(
                applicationContext,
                "wifi on",
                Toast.LENGTH_SHORT
            ).show()
            wifiManager.isWifiEnabled = true
            updateTile(true)
        }




    }
}