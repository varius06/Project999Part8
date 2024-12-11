package com.example.course999part8.dashboard

import com.example.course999part8.core.HideAndShow

interface PremiumDashboardUiState {

    fun show(button: HideAndShow, text: HideAndShow)

    object Playing : PremiumDashboardUiState {
        override fun show(button: HideAndShow, text: HideAndShow) {
            button.hide()
            text.show()
        }
    }
}