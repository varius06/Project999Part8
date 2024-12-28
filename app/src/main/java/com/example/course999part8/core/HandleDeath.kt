package com.example.course999part8.core

interface HandleDeath {
    fun firstOpening()

    fun didDeathHappened(): Boolean

    fun deathHandled()

    class Base : HandleDeath {
        private var deathHappened = true

        override fun firstOpening() {
            deathHappened = false
        }

        override fun didDeathHappened(): Boolean {
            return deathHappened
        }

        override fun deathHandled() {
            deathHappened = false
        }

    }
}