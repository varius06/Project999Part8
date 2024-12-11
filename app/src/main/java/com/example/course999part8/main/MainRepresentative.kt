package com.example.course999part8.main

import com.example.course999part8.core.Representative
import com.example.course999part8.core.UiObserver
import com.example.course999part8.dashboard.DashboardScreen

interface MainRepresentative : Representative<Screen> {


    fun showDashBoard(firstTime: Boolean)

    class Base(
        private val navigation: Navigation.Mutable
    ) : MainRepresentative {
        override fun startGettingUpdates(callback: UiObserver<Screen>) {
            navigation.updateObserver(callback)
        }

        override fun stopGettingUpdates() {
            navigation.updateObserver()
        }

        override fun showDashBoard(firstTime: Boolean) {
            if (firstTime) {
                navigation.update(DashboardScreen)
            }
        }

    }
}

