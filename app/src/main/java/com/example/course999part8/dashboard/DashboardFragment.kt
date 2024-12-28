package com.example.course999part8.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.course999part8.R
import com.example.course999part8.core.CustomButton
import com.example.course999part8.core.CustomTextView
import com.example.course999part8.core.ProvideRepresentative
import com.example.course999part8.core.UiObserver

class DashboardFragment : Fragment() {

    private lateinit var callback: UiObserver<PremiumDashboardUiState>
    private lateinit var dashboardRepresentative: DashboardRepresentative
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardRepresentative =
            (requireActivity() as ProvideRepresentative).provideRepresentative(
                DashboardRepresentative::class.java
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<CustomButton>(R.id.playButton)
        val textView = view.findViewById<CustomTextView>(R.id.showPlayingTextView)
        button.setOnClickListener { dashboardRepresentative.play() }
        callback = object : DashboardObserver {
            override fun update(data: PremiumDashboardUiState) {
                data.show(button, textView)
            }

        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("varius", "Dashboard onResume")
        dashboardRepresentative.startGettingUpdates(callback)
    }

    override fun onPause() {
        super.onPause()
        Log.d("varius", "Dashboard onPause")
        dashboardRepresentative.stopGettingUpdates()
    }
}

interface DashboardObserver : UiObserver<PremiumDashboardUiState> {
    override fun isEmpty() = false
}