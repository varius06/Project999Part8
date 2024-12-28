package com.example.course999part8.subscription

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.course999part8.R
import com.example.course999part8.core.CustomButton
import com.example.course999part8.core.CustomProgressBar
import com.example.course999part8.core.UiObserver
import com.example.course999part8.main.BaseFragment

class SubscriptionFragment :
    BaseFragment<SubscriptionRepresentative>(R.layout.fragment_subscription) {

    private lateinit var observer: UiObserver<SubscriptionUiState>
    override val clasz: Class<SubscriptionRepresentative>
        get() = SubscriptionRepresentative::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val subscribeButton = view.findViewById<CustomButton>(R.id.subscribeButton)
        val progressBar = view.findViewById<CustomProgressBar>(R.id.progressBar)
        val finishButton = view.findViewById<CustomButton>(R.id.finishButton)
        subscribeButton.setOnClickListener { representative.subscribe() }
        /*  activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
              override fun handleOnBackPressed() {
                  representative.clear()
              }
          })*/
        finishButton.setOnClickListener { representative.finish() }

        observer = object : SubscriptionObserver {
            override fun update(data: SubscriptionUiState) = requireActivity().runOnUiThread() {
                data.observed(representative)
                data.show(subscribeButton, progressBar, finishButton)
            }

        }

        representative.init(SaveAndRestoreSubscriptionUiState.Base(savedInstanceState))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        representative.save(SaveAndRestoreSubscriptionUiState.Base(outState))
    }

    override fun onResume() {
        super.onResume()
        representative.startGettingUpdates(observer)
        Log.d("varius", "Subscription onResume")
    }

    override fun onPause() {
        super.onPause()
        representative.stopGettingUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("varius", "Subscription onDestroy")
    }

}

interface SubscriptionObserver : UiObserver<SubscriptionUiState> {
    override fun isEmpty() = false
}