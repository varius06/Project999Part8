package com.example.course999part8.subscription

import android.util.Log
import com.example.course999part8.core.ClearRepresentative
import com.example.course999part8.core.Representative
import com.example.course999part8.dashboard.DashboardRepresentative
import com.example.course999part8.dashboard.DashboardScreen
import com.example.course999part8.main.Navigation
import com.example.course999part8.main.UserPremiumCache

interface SubscriptionRepresentative : Representative<Unit> {

    fun subscribe()

    //fun clear()

    class Base(
        private val clear: ClearRepresentative,
        private val userPremiumCache: UserPremiumCache.Save,
        private val navigation: Navigation.Update
    ) : SubscriptionRepresentative {

        init {
            Log.d("varius", "SubscriptionRepresentative init")
        }

        //        override fun clear() {
//            clear.clear(SubscriptionRepresentative::class.java)
//            navigation.update(Screen.Dashboard)
//        }
        override fun subscribe() {
            userPremiumCache.saveUserPremium()
            clear.clear(DashboardRepresentative::class.java)
            clear.clear(SubscriptionRepresentative::class.java)
            //clear()
            navigation.update(DashboardScreen)
        }

    }
}