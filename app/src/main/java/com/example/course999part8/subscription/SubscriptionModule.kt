package com.example.course999part8.subscription

import com.example.course999part8.core.ClearRepresentative
import com.example.course999part8.core.Core
import com.example.course999part8.core.HandleDeath
import com.example.course999part8.core.Module
import com.example.course999part8.main.UserPremiumCache

class SubscriptionModule(
    private val core: Core,
    private val clear: ClearRepresentative
) : Module<SubscriptionRepresentative> {


    override fun representative() = SubscriptionRepresentative.Base(
        HandleDeath.Base(),
        SubscriptionObservable.Base(),
        clear,
        UserPremiumCache.Base(core.sharedPreferences()),
        core.navigation()
    )


}