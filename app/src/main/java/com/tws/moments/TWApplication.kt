package com.tws.moments

import android.app.Application
import com.tws.moments.utils.ScreenAdaptiveUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TWApplication : Application() {

    companion object {
        lateinit var app: Application
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        ScreenAdaptiveUtil.adaptive(this)
    }

}
