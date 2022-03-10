package com.example.mobilecomputing

import android.app.Application
import com.example.mobilecomputing.Graph

class MobileComputingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}