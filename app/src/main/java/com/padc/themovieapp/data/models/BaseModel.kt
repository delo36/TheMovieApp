package com.padc.themovieapp.data.models

import android.content.Context

import com.padc.themovieapp.network.responses.TheMovieApi
import com.padc.themovieapp.persistence.MovieDatabase
import com.padc.themovieapp.utils.BASE_URL

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {

    protected var mTheMovieApi: TheMovieApi
    protected var mMovieDatabase: MovieDatabase? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        mTheMovieApi = retrofit.create(TheMovieApi::class.java)
    }

    fun initDatabase(context: Context){
        mMovieDatabase = MovieDatabase.getDBInstance(context)
    }

}