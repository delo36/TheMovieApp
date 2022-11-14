package com.padc.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padc.themovieapp.data.models.MovieModelImpl
import com.padc.themovieapp.data.vos.ActorVO
import com.padc.themovieapp.data.vos.GenreVO
import com.padc.themovieapp.data.vos.MovieVO

class MainViewModel: ViewModel() {

    //Model
    private val mMovieModel = MovieModelImpl

    //LiveData
    var nowPlayingMovieLiveData : LiveData<List<MovieVO>>? = null
    var popularMovieLiveData : LiveData<List<MovieVO>>? = null
    var topRatedMovieLiveData : LiveData<List<MovieVO>>? = null
    val genresLiveData = MutableLiveData<List<GenreVO>>()
    val moviesByGenreLiveData = MutableLiveData<List<MovieVO>>()
    val actorsLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(){
        nowPlayingMovieLiveData = mMovieModel.getNowPlayingMovies { mErrorLiveData.postValue(it) }
        popularMovieLiveData = mMovieModel.getPopularMovies { mErrorLiveData.postValue(it) }
        topRatedMovieLiveData = mMovieModel.getTopRatedMovies { mErrorLiveData.postValue(it) }

        mMovieModel.getGenres(
            onSuccess = {
                genresLiveData.postValue(it)
                getMovieByGenre(0)
            }, onFailure = {
                mErrorLiveData.postValue(it)
            }
        )

        mMovieModel.getActors(
            onSuccess = {
                actorsLiveData.postValue(it)
            }, onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
    }

    public fun getMovieByGenre(genrePosition: Int) {
        genresLiveData.value?.getOrNull(genrePosition)?.id?.let {
            mMovieModel.getMoviesByGenre(it.toString(), onSuccess = { moviesByGenre ->
                moviesByGenreLiveData.postValue(moviesByGenre)
            }, onFailure = {
                mErrorLiveData.postValue(it)
            })
        }
    }
}