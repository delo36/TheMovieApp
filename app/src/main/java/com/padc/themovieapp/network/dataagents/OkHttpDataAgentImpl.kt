package com.padc.themovieapp.network.dataagents

import android.os.AsyncTask
import com.google.gson.Gson
import com.padc.themovieapp.data.vos.ActorVO
import com.padc.themovieapp.data.vos.GenreVO
import com.padc.themovieapp.data.vos.MovieVO
import com.padc.themovieapp.network.responses.GetActorResponse
import com.padc.themovieapp.network.responses.GetCreditsByMovieResponse
import com.padc.themovieapp.network.responses.MovieListResponse
import com.padc.themovieapp.utils.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

object OkHttpDataAgentImpl : MovieDataAgent {

    private val mClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetNowPlayingMovieOkHttpTask(mClient).execute()
    }

    override fun getActors(
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetActorsOkHttpTask(mClient).execute()
    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
        GetPopularMovieOkHttpTask(mClient).execute()
    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetTopRatedMovieOkHttpTask(mClient).execute()
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        GetGenresOkHttpTask(mClient).execute()
    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetMoviesByGenreOkHttpTask(mClient).execute()
    }

    override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetMovieDetailOkHttpTask(mClient).execute()
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetCreditsByMovieOkHttpTask(mClient).execute()
    }

    class GetCreditsByMovieOkHttpTask(
        private val mOkHttpClient: OkHttpClient
    ) : AsyncTask<Void, Void, GetCreditsByMovieResponse>() {
        override fun doInBackground(vararg p0: Void?): GetCreditsByMovieResponse? {
            val request = Request.Builder()
                .url(""" $BASE_URL$API_GET_CREDITS_BY_MOVIE?api_key=$MOVIES_API_KEY&language=en-US&page=1""")
                .build()

            try {
                val response = mOkHttpClient.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.let {
                        val responseString = it.string()
                        val response = Gson().fromJson<GetCreditsByMovieResponse>(
                            responseString, GetCreditsByMovieResponse::class.java
                        )
                        return response
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }

    class GetMovieDetailOkHttpTask(
        private val mOkHttpClient: OkHttpClient
    ) : AsyncTask<Void, Void, MovieListResponse>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val request = Request.Builder()
                .url(""" $BASE_URL$API_GET_MOVIE_DETAILS?api_key=$MOVIES_API_KEY&language=en-US&page=1""")
                .build()

            try {
                val response = mOkHttpClient.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.let {
                        val responseString = it.string()
                        val response = Gson().fromJson<MovieListResponse>(
                            responseString, MovieListResponse::class.java
                        )
                        return response
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }

    class GetMoviesByGenreOkHttpTask(
        private val mOkHttpClient: OkHttpClient
    ) : AsyncTask<Void, Void, MovieListResponse>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val request = Request.Builder()
                .url(""" $BASE_URL$API_GET_GENRE?api_key=$MOVIES_API_KEY&language=en-US&page=1""")
                .build()

            try {
                val response = mOkHttpClient.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.let {
                        val responseString = it.string()
                        val response = Gson().fromJson<MovieListResponse>(
                            responseString, MovieListResponse::class.java
                        )
                        return response
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }


    class GetGenresOkHttpTask(
        private val mOkHttpClient: OkHttpClient
    ) : AsyncTask<Void, Void, MovieListResponse>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val request = Request.Builder()
                .url(""" $BASE_URL$API_GET_GENRE?api_key=$MOVIES_API_KEY&language=en-US&page=1""")
                .build()

            try {
                val response = mOkHttpClient.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.let {
                        val responseString = it.string()
                        val response = Gson().fromJson<MovieListResponse>(
                            responseString, MovieListResponse::class.java
                        )
                        return response
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }

    class GetNowPlayingMovieOkHttpTask(
        private val mOkHttpClient: OkHttpClient
    ) : AsyncTask<Void, Void, MovieListResponse>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val request = Request.Builder()
                .url(""" $BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIES_API_KEY&language=en-US&page=1""")
                .build()

            try {
                val response = mOkHttpClient.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.let {
                        val responseString = it.string()
                        val response = Gson().fromJson<MovieListResponse>(
                            responseString, MovieListResponse::class.java
                        )
                        return response
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }

    class GetPopularMovieOkHttpTask(
        private val mOkHttpClient: OkHttpClient
    ) : AsyncTask<Void, Void, MovieListResponse>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val request = Request.Builder()
                .url(""" $BASE_URL$API_GET_POPULAR_MOVIES?api_key=$MOVIES_API_KEY&language=en-US&page=1""")
                .build()

            try {
                val response = mOkHttpClient.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.let {
                        val responseString = it.string()
                        val response = Gson().fromJson<MovieListResponse>(
                            responseString, MovieListResponse::class.java
                        )
                        return response
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }

    class GetTopRatedMovieOkHttpTask(
        private val mOkHttpClient: OkHttpClient
    ) : AsyncTask<Void, Void, MovieListResponse>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val request = Request.Builder()
                .url(""" $BASE_URL$API_GET_TOP_RATED_MOVIES?api_key=$MOVIES_API_KEY&language=en-US&page=1""")
                .build()

            try {
                val response = mOkHttpClient.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.let {
                        val responseString = it.string()
                        val response = Gson().fromJson<MovieListResponse>(
                            responseString, MovieListResponse::class.java
                        )
                        return response
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }
}
class GetActorsOkHttpTask(
    private val mOkHttpClient: OkHttpClient
) : AsyncTask<Void, Void, GetActorResponse>() {
    override fun doInBackground(vararg p0: Void?): GetActorResponse? {
        val request = Request.Builder()
            .url(""" $BASE_URL$API_GET_ACTORS?api_key=$MOVIES_API_KEY&language=en-US&page=1""")
            .build()

        try {
            val response = mOkHttpClient.newCall(request).execute()

            if (response.isSuccessful) {
                response.body?.let {
                    val responseString = it.string()
                    val response = Gson().fromJson<GetActorResponse>(
                        responseString, GetActorResponse::class.java
                    )
                    return response
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}



