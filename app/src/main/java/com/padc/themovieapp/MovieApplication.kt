package com.padc.themovieapp

import android.app.Application
import com.padc.themovieapp.data.models.MovieModelImpl

class MovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDatabase(applicationContext)
    }

}