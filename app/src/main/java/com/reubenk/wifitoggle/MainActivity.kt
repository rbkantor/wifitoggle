package com.reubenk.wifitoggle

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.quicksettings.TileService
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }
    val myReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action.equals(
                    "android.net.wifi.STATE_CHANGE",
                    ignoreCase = true
                )
            ) {
                Log.d("log", "Brandon let's go")
                TileService.requestListeningState(context, componentName)
             //   updateTile(null)
            }
        }
    }

    private var filter : IntentFilter? = null


    fun setup() {
        filter = IntentFilter("android.net.wifi.STATE_CHANGE");
        registerReceiver(myReceiver, filter);




    }

}