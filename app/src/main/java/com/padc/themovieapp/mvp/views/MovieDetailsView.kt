package com.padc.themovieapp.mvp.views

import com.padc.themovieapp.data.vos.ActorVO
import com.padc.themovieapp.data.vos.MovieVO

interface MovieDetailsView: BaseView {
    fun showMovieDetails(movie: MovieVO)
    fun showCreditsByMovie(cast: List<ActorVO>,crew: List<ActorVO>)
    fun navigationBack()
}