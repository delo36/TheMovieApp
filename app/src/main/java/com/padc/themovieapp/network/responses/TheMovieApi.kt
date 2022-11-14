package com.padc.themovieapp.network.responses

import com.padc.themovieapp.data.vos.MovieVO
import com.padc.themovieapp.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {
    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIES_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Observable<MovieListResponse>

    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIES_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED_MOVIES)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIES_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Observable<MovieListResponse>

    @GET(API_GET_ACTORS)
    fun getActors(
        @Query(PARAM_API_KEY) apiKey: String = MOVIES_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Observable<GetActorResponse>

    @GET(API_GET_GENRE)
    fun getGenres(
        @Query(PARAM_API_KEY) apiKey: String = MOVIES_API_KEY,
        ): Observable<GetGenresResponse>

    @GET(API_GET_MOVIES_BY_GENRE)
    fun getMoviesByGenre(
        @Query(PARAM_API_KEY) apiKey: String = MOVIES_API_KEY,
        @Query(PARAM_GENRE_KEY) genreId: String ,
        ): Observable<MovieListResponse>

    @GET("$API_GET_MOVIE_DETAILS/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId:String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIES_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Observable<MovieVO>

    @GET("$API_GET_CREDITS_BY_MOVIE/{movie_id}/credits")
    fun getCreditsByMovie(
        @Path("movie_id") movieId:String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIES_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Observable<GetCreditsByMovieResponse>


    @GET(API_SEARCH_MOVIES)
    fun searchMovie(

        @Query(PARAM_API_KEY) apiKey: String = MOVIES_API_KEY,
        @Query(PARAM_QUERY) query: String,
    ): Observable<MovieListResponse>

}