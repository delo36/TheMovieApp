package com.padc.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.themovieapp.mvp.views.MainView
import com.padc.themovieapp.mvp.views.MovieDetailsView

interface MovieDetailsPresenter: IBasePresenter {
    fun initView(view: MovieDetailsView)
    fun onUiReadyInMovieDetails(owner: LifecycleOwner,movieId: Int)
    fun onTapBack()
}