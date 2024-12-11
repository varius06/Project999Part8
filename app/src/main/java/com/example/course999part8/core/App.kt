package com.example.course999part8.core

import android.app.Application
import android.util.Log


class App : Application(), ProvideRepresentative, ClearRepresentative {

    private val representativeMap = mutableMapOf<Class<out Representative<*>>, Representative<*>>()

    private lateinit var core: Core
    private lateinit var factory: ProvideRepresentative.Factory

    override fun onCreate() {
        super.onCreate()
        core = Core.Base(this)
        factory = ProvideRepresentative.Factory(core, this)
    }

    override fun clear(clasz: Class<out Representative<*>>) {
        representativeMap.remove(clasz)
    }

    override fun <T : Representative<*>> provideRepresentative(clasz: Class<T>): T {
        return if (representativeMap.containsKey(clasz)) {
            representativeMap[clasz] as T
        } else {
            val representative = factory.provideRepresentative(clasz)
            representativeMap[clasz] = representative
            representative
        }
    }

    private val handleDeath = HandleDeath.Base()
    fun activityCreated(firstOpening: Boolean) {

        if (firstOpening) {
            Log.d("course", "very first time")
            handleDeath.firstOpening()
        } else {
            if (handleDeath.wasDeathHappened()) {
                Log.d("course", "death happened")
                handleDeath.deathHandled()
            } else {
                Log.d("course", "just activity recreated")
            }
        }

    }


}