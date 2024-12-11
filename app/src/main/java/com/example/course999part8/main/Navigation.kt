package com.example.course999part8.main

import com.example.course999part8.core.UiObservable
import com.example.course999part8.core.UiUpdate
import com.example.course999part8.core.UpdateObserver

interface Navigation {

    interface Update : UiUpdate<Screen>

    interface Observe : UpdateObserver<Screen>

    interface Mutable : Update, Observe

    class Base : UiObservable.Single<Screen>(), Mutable
}