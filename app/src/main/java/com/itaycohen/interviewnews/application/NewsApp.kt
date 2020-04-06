package com.itaycohen.interviewnews.application

import android.app.Application
import com.itaycohen.interviewnews.application.di.AppComponent
import com.itaycohen.interviewnews.application.di.DaggerAppComponent

class NewsApp : Application() {

    val appComponent: AppComponent by lazy {
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.factory().create(applicationContext)
    }

}