package com.example.course999part8.dashboard

import com.example.course999part8.core.Core
import com.example.course999part8.core.Module
import com.example.course999part8.main.UserPremiumCache

class DashboardModule(
    private val core: Core
) : Module<DashboardRepresentative> {
    override fun representative(): DashboardRepresentative {
        val cache = UserPremiumCache.Base(core.sharedPreferences())
        return if (cache.isUserPremium()) {
            DashboardRepresentative.Premium(PremiumDashboardObservable.Base())
        } else {
            DashboardRepresentative.Base(core.navigation())
        }
    }
}