package com.example.course999part8.main

import com.example.course999part8.core.Core
import com.example.course999part8.core.Module

class MainModule(private val core: Core) : Module<MainRepresentative> {
    override fun representative(): MainRepresentative {
        return MainRepresentative.Base(core.navigation())
    }
}