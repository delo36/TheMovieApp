package com.padc.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padc.themovieapp.data.models.MovieModelImpl
import com.padc.themovieapp.mvp.views.MovieDetailsView

class MainDetailsPresenterImpl : ViewModel(),MovieDetailsPresenter {

    //Model
    private val mMovieModel = MovieModelImpl

    //View
    private var mView:MovieDetailsView? = null


    override fun initView(view: MovieDetailsView) {
       mView = view
    }

    override fun onUiReadyInMovieDetails(owner: LifecycleOwner, movieId: Int) {
        //MovieDetail
        mMovieModel.getMovieDetail(movieId.toString()){
            mView?.showError(it)
        }?.observe(owner){
            it?.let {
                mView?.showMovieDetails(it)
            }
        }

        //Credits
        mMovieModel.getCreditsByMovie(movieId = movieId.toString(), onSuccess = {
            mView?.showCreditsByMovie(cast = it.first, crew = it.second)
        }, onFailure = {
            mView?.showError(it)
        })
    }


    override fun onTapBack() {
       mView?.navigationBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

}