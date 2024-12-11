package com.example.course999part8.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.course999part8.R
import com.example.course999part8.core.ProvideRepresentative
import com.example.course999part8.core.Representative
import com.example.course999part8.core.UiObserver

class MainActivity : AppCompatActivity(), ProvideRepresentative {

    private lateinit var representative: MainRepresentative
    private lateinit var activityCallback: ActivityCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val view = layoutInflater.inflate(R.layout.fragment_subscription, null)
        representative = provideRepresentative(MainRepresentative::class.java)
        activityCallback = object : ActivityCallback {
            override fun update(data: Screen) {
                data.show(supportFragmentManager, R.id.container)
            }
        }
        representative.showDashBoard(savedInstanceState == null)
    }

    override fun onResume() {
        super.onResume()
        representative.startGettingUpdates(activityCallback)
    }

    override fun onPause() {
        super.onPause()
        representative.stopGettingUpdates()
    }

    override fun <T : Representative<*>> provideRepresentative(clasz: Class<T>): T {
        return (application as ProvideRepresentative).provideRepresentative(clasz)
    }
}

interface ActivityCallback : UiObserver<Screen> {

}