package com.itaycohen.interviewnews

import android.app.Application
import com.itaycohen.interviewnews.dependency_injection.AppComponent
import com.itaycohen.interviewnews.dependency_injection.DaggerAppComponent

class NewsApp : Application() {

    val appComponent: AppComponent by lazy {
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.factory().create(applicationContext)
    }

}