package com.example.course999part8.dashboard

import com.example.course999part8.core.UiObservable

interface PremiumDashboardObservable : UiObservable<PremiumDashboardUiState> {
    class Base : UiObservable.Single<PremiumDashboardUiState>(), PremiumDashboardObservable
}