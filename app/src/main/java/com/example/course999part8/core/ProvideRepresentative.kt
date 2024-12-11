package com.example.course999part8.core

import com.example.course999part8.dashboard.DashboardModule
import com.example.course999part8.dashboard.DashboardRepresentative
import com.example.course999part8.main.MainModule
import com.example.course999part8.main.MainRepresentative
import com.example.course999part8.subscription.SubscriptionModule
import com.example.course999part8.subscription.SubscriptionRepresentative

interface ProvideRepresentative {

    fun <T : Representative<*>> provideRepresentative(clasz: Class<T>): T

    class Factory(
        private val core: Core,
        private val clear: ClearRepresentative
    ) : ProvideRepresentative {
        override fun <T : Representative<*>> provideRepresentative(clasz: Class<T>): T {
            return when (clasz) {
                MainRepresentative::class.java -> MainModule(core).representative()
                DashboardRepresentative::class.java -> DashboardModule(core).representative()
                SubscriptionRepresentative::class.java -> SubscriptionModule(
                    core,
                    clear
                ).representative()

                else -> throw IllegalStateException("unknown class \$clasz")
            } as T
        }

    }
}