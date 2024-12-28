package com.example.course999part8.subscription

import android.util.Log
import com.example.course999part8.core.ClearRepresentative
import com.example.course999part8.core.HandleDeath
import com.example.course999part8.core.Representative
import com.example.course999part8.core.UiObserver
import com.example.course999part8.dashboard.DashboardRepresentative
import com.example.course999part8.dashboard.DashboardScreen
import com.example.course999part8.main.Navigation
import com.example.course999part8.main.UserPremiumCache

interface SubscriptionRepresentative : Representative<SubscriptionUiState>,
    SaveSubscriptionUiState, SubscriptionObserved, SubscriptionInner {
    fun init(restoreState: SaveAndRestoreSubscriptionUiState.Restore)
    fun subscribe()
    fun finish()

    //fun clear()

    class Base(
        private val handleDeath: HandleDeath,
        private val observable: SubscriptionObservable,
        private val clear: ClearRepresentative,
        private val userPremiumCache: UserPremiumCache.Save,
        private val navigation: Navigation.Update
    ) : SubscriptionRepresentative {

        init {
            Log.d("varius", "SubscriptionRepresentative init")
        }

        override fun observed() = observable.clear()

        override fun init(restoreState: SaveAndRestoreSubscriptionUiState.Restore) {
            if (restoreState.isEmpty()) {
                handleDeath.firstOpening()
                observable.update(SubscriptionUiState.Initial)
                Log.d("course", "very first time")
            } else {
                if (handleDeath.didDeathHappened()) {
                    Log.d("course", "death happened")
                    handleDeath.deathHandled()
                    val uiState = restoreState.restore()
                    Log.d("varius", "SubscriptionRepresentative#restoreAfterDeath")
                    uiState.restoreAfterDeath(this, observable)

                } else {
                    Log.d("course", "just activity recreated")
                }
            }
        }

        //        override fun clear() {
//            clear.clear(SubscriptionRepresentative::class.java)
//            navigation.update(Screen.Dashboard)
//        }

        override fun save(saveState: SaveAndRestoreSubscriptionUiState.Save) {
            observable.save(saveState)
        }

        private fun thread() = Thread {
            Thread.sleep(10000)
            userPremiumCache.saveUserPremium()
            observable.update(SubscriptionUiState.Success)
        }
        override fun subscribe() {
            observable.update(SubscriptionUiState.Loading)
            subscribeInner()

            //clear()

        }

        override fun subscribeInner() {
            Log.d("varius", "SubscriptionRepresentative#subscribeInner")
            thread().start()
        }

        override fun finish() {
            clear.clear(DashboardRepresentative::class.java)
            clear.clear(SubscriptionRepresentative::class.java)
            navigation.update(DashboardScreen)
        }

        override fun startGettingUpdates(callback: UiObserver<SubscriptionUiState>) {
            observable.updateObserver(callback)
        }

        override fun stopGettingUpdates() {
            observable.updateObserver()
        }

    }
}

interface SaveSubscriptionUiState {
    fun save(saveState: SaveAndRestoreSubscriptionUiState.Save)
}

interface SubscriptionObserved {
    fun observed()
}

interface SubscriptionInner {
    fun subscribeInner()
}