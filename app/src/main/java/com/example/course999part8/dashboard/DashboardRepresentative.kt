package com.example.course999part8.dashboard

import com.example.course999part8.core.Representative
import com.example.course999part8.core.UiObserver
import com.example.course999part8.main.Navigation
import com.example.course999part8.subscription.SubscriptionScreen

interface DashboardRepresentative : Representative<PremiumDashboardUiState> {

    fun play()


    class Base(private val navigation: Navigation.Update) : DashboardRepresentative {
        override fun play() {
            navigation.update(SubscriptionScreen)
        }

    }

    class Premium(
        private val observable: PremiumDashboardObservable
    ) : DashboardRepresentative {
        override fun play() {
            observable.update(PremiumDashboardUiState.Playing)
        }

        override fun startGettingUpdates(callback: UiObserver<PremiumDashboardUiState>) {
            observable.updateObserver(callback)
        }

        override fun stopGettingUpdates() {
            observable.updateObserver()
        }

    }
}