package com.example.course999part8.subscription

import com.example.course999part8.core.UiObservable

interface SubscriptionObservable : UiObservable<SubscriptionUiState>, SaveSubscriptionUiState {
    class Base : UiObservable.Single<SubscriptionUiState>(SubscriptionUiState.Empty),
        SubscriptionObservable {
        override fun save(saveState: SaveAndRestoreSubscriptionUiState.Save) {
            saveState.save(cache)
        }
    }
}