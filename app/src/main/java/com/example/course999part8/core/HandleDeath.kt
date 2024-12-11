package com.example.course999part8.core

interface HandleDeath {
    fun firstOpening()

    fun wasDeathHappened(): Boolean

    fun deathHandled()

    class Base : HandleDeath {
        private var deathHappened = true

        override fun firstOpening() {
            deathHappened = false
        }

        override fun wasDeathHappened(): Boolean {
            return deathHappened
        }

        override fun deathHandled() {
            deathHappened = false
        }

    }
}