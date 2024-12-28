package com.example.course999part8.subscription

import android.os.Bundle
import com.example.course999part8.core.SaveAndRestoreState

interface SaveAndRestoreSubscriptionUiState {
    interface Save : SaveAndRestoreState.Save<SubscriptionUiState>

    interface Restore : SaveAndRestoreState.Restore<SubscriptionUiState>

    interface Mutable : Save, Restore

    class Base(
        bundle: Bundle?
    ) : SaveAndRestoreState.Abstract<SubscriptionUiState>(
        bundle,
        KEY,
        SubscriptionUiState::class.java
    ), Mutable

    companion object {
        private const val KEY = "SubscriptionUiStateSaveAndRestoreKey"
    }
}
