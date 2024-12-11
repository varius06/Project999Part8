package com.example.course999part8.subscription

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.course999part8.R
import com.example.course999part8.main.BaseFragment

class SubscriptionFragment :
    BaseFragment<SubscriptionRepresentative>(R.layout.fragment_subscription) {
    override val clasz: Class<SubscriptionRepresentative>
        get() = SubscriptionRepresentative::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.subscribeButton)
        button.setOnClickListener { representative.subscribe() }
        /*  activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
              override fun handleOnBackPressed() {
                  representative.clear()
              }
          })*/
    }

    override fun onResume() {
        super.onResume()
        Log.d("varius", "Subscription onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("varius", "Subscription onDestroy")
    }

}