package com.padc.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padc.themovieapp.data.models.MovieModel
import com.padc.themovieapp.data.models.MovieModelImpl
import com.padc.themovieapp.data.vos.GenreVO
import com.padc.themovieapp.mvp.views.MainView

class MainPresenterImpl : ViewModel(),MainPresenter{

    //View
    var mView: MainView? = null

    //Model
    private val mMovieModel: MovieModel =MovieModelImpl

    //States
    private var mGenres: List<GenreVO>? = listOf()

    override fun initView(view: MainView) {
       mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        //NowPlaying

        mMovieModel.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showNowPlayingMovies(it)
        }

        //PopularMovies

        mMovieModel.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showPopularMovies(it)
        }

        //TopRatedMovies

        mMovieModel.getTopRatedMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showTopRatedMovies(it)
        }

        //Genre and Get Movies For Genre

        mMovieModel.getGenres(
            onSuccess = {
                mGenres = it
                mView?.showGenres(it)
                it.firstOrNull()?.id?.let { firstGenreId ->
                    onTapGenre(firstGenreId)
                }
            }, onFailure = {
                mView?.showError(it)
            }
        )

        //Actors

        mMovieModel.getActors(
            onSuccess = {
                mView?.showActors(it)
            }, onFailure = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapGenre(genrePosition: Int) {
        mGenres?.getOrNull(genrePosition)?.id?.let { genreId ->
            mMovieModel.getMoviesByGenre(genreId = genreId.toString(), onSuccess = {
                mView?.showMoviesByGenre(it)
            }, onFailure = {
                mView?.showError(it)
            })
        }
    }



    override fun onTapMovieFromBanner(movieId: Int) {
        mView?.navigateToMovieDetailScreen(movieId)
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
       mView?.navigateToMovieDetailScreen(movieId)
    }

    override fun onTapMovie(movieId: Int) {
        mView?.navigateToMovieDetailScreen(movieId)
    }
}