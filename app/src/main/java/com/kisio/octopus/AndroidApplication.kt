package com.kisio.octopus

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.kisio.octopus.core.di.ApplicationComponent
import com.kisio.octopus.core.di.ApplicationModule
import com.kisio.octopus.core.di.DaggerApplicationComponent
import ss.com.bannerslider.Slider

class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        this.initializeLeakDetection()
        Slider.init(PicassoImageLoadingService(this));
    }

    private fun injectMembers() = appComponent.inject(this)

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}

