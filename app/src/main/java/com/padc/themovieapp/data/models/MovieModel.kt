package com.padc.themovieapp.data.models

import androidx.lifecycle.LiveData
import com.padc.themovieapp.data.vos.ActorVO
import com.padc.themovieapp.data.vos.GenreVO
import com.padc.themovieapp.data.vos.MovieVO

interface MovieModel {
    fun getNowPlayingMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>?
    fun getPopularMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getActors(
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun getTopRatedMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getGenres(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetail(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>?

    fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>,List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    )
}