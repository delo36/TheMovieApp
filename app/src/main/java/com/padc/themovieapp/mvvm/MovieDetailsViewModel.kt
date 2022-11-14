package com.padc.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padc.themovieapp.data.models.MovieModelImpl
import com.padc.themovieapp.data.vos.ActorVO
import com.padc.themovieapp.data.vos.MovieVO

class MovieDetailsViewModel : ViewModel(){
    //Model
    private val mMovieModel = MovieModelImpl

    //Live Data
    var movieDetailsLivedData : LiveData<MovieVO?>? = null
    val castLiveData = MutableLiveData<List<ActorVO>>()
    val crewLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(movieId: Int){
        movieDetailsLivedData = mMovieModel.getMovieDetail(movieId = movieId.toString()){mErrorLiveData.postValue(it)}

        mMovieModel.getCreditsByMovie(movieId.toString(), onSuccess = {
            castLiveData.postValue(it.first ?: listOf())
            crewLiveData.postValue(it.second ?: listOf())
        }, onFailure = {
            mErrorLiveData.postValue(it)
        })
    }
}