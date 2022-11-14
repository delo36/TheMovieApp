package com.padc.themovieapp.mvp.presenters

import com.padc.themovieapp.delegates.BannerViewHolderDelegate
import com.padc.themovieapp.delegates.MovieViewHolderDelegate
import com.padc.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padc.themovieapp.mvp.views.MainView

interface MainPresenter : IBasePresenter,BannerViewHolderDelegate,ShowcaseViewHolderDelegate,MovieViewHolderDelegate{
    fun initView(view: MainView)
    fun onTapGenre(genrePosition: Int)
}