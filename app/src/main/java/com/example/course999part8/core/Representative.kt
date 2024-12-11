package com.example.course999part8.core

interface Representative<T : Any> {

    fun startGettingUpdates(callback: UiObserver<T>) = Unit
    fun stopGettingUpdates() = Unit
}