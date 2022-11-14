package com.padc.themovieapp.network.dataagents

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.padc.themovieapp.data.vos.ActorVO
import com.padc.themovieapp.data.vos.GenreVO
import com.padc.themovieapp.data.vos.MovieVO
import com.padc.themovieapp.network.responses.GetActorResponse
import com.padc.themovieapp.network.responses.GetCreditsByMovieResponse
import com.padc.themovieapp.network.responses.MovieListResponse
import com.padc.themovieapp.utils.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object MovieDataAgentImpl : MovieDataAgent {


    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetNowPlayingMovieTask().execute()
    }


    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
        GetPopularMoviesTask().execute()
    }

    override fun getActors(
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetActorsTask().execute()
    }


    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetTopRatedMoviesTask().execute()
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        GetGenresTask().execute()
    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
       GetMoviesByGenreTask().execute()
    }

    override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetMovieDetailTask().execute()
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetCreditsByMovieTask().execute()
    }

    class GetActorsTask() : AsyncTask<Void, Void, GetActorResponse>() {
        override fun doInBackground(vararg p0: Void?): GetActorResponse? {
            val url: URL
            var reader: BufferedReader? = null
            val stringBuilder: StringBuilder

            try {
                url =
                    URL(""" $BASE_URL$API_GET_ACTORS?api_key=$MOVIES_API_KEY&language=en-US&page=1""")

                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"

                connection.readTimeout = 15 * 1000

                connection.doInput = true
                connection.doOutput = false

                connection.connect()

                reader = BufferedReader(InputStreamReader(connection.inputStream))
                stringBuilder = StringBuilder()

                for (line in reader.readLines()) {
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("Get Actors", responseString)

                val movieListResponse =
                    Gson().fromJson(responseString, GetActorResponse::class.java)

                return movieListResponse

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("Errors", "" + e.message ?: "")
            } finally {
                if (reader != null) {
                    try {
                        reader.close()
                    } catch (ioe: Exception) {
                        ioe.printStackTrace()
                    }
                }
            }
            return null
        }

    }

    class GetNowPlayingMovieTask() : AsyncTask<Void, Void, MovieListResponse>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val url: URL
            var reader: BufferedReader? = null
            val stringBuilder: StringBuilder

            try {
                url =
                    URL(""" $BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIES_API_KEY&language=en-US&page=1""")

                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"

                connection.readTimeout = 15 * 1000

                connection.doInput = true
                connection.doOutput = false

                connection.connect()

                reader = BufferedReader(InputStreamReader(connection.inputStream))
                stringBuilder = StringBuilder()

                for (line in reader.readLines()) {
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("NowPlayingMovies", responseString)

                val movieListResponse =
                    Gson().fromJson(responseString, MovieListResponse::class.java)

                return movieListResponse

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("Errors", "" + e.message ?: "")
            } finally {
                if (reader != null) {
                    try {
                        reader.close()
                    } catch (ioe: Exception) {
                        ioe.printStackTrace()
                    }
                }
            }
            return null
        }

    }

    class GetPopularMoviesTask() : AsyncTask<Void, Void, MovieListResponse>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val url: URL
            var reader: BufferedReader? = null
            val stringBuilder: StringBuilder

            try {
                url =
                    URL(""" $BASE_URL$API_GET_POPULAR_MOVIES?api_key=$MOVIES_API_KEY&language=en-US&page=1""")

                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"

                connection.readTimeout = 15 * 1000

                connection.doInput = true
                connection.doOutput = false

                connection.connect()

                reader = BufferedReader(InputStreamReader(connection.inputStream))
                stringBuilder = StringBuilder()

                for (line in reader.readLines()) {
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("PopularMovies", responseString)

                val movieListResponse =
                    Gson().fromJson(responseString, MovieListResponse::class.java)

                return movieListResponse

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("Errors", "" + e.message ?: "")
            } finally {
                if (reader != null) {
                    try {
                        reader.close()
                    } catch (ioe: Exception) {
                        ioe.printStackTrace()
                    }
                }
            }
            return null
        }

    }

    class GetTopRatedMoviesTask() : AsyncTask<Void, Void, MovieListResponse>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val url: URL
            var reader: BufferedReader? = null
            val stringBuilder: StringBuilder

            try {
                url =
                    URL(""" $BASE_URL$API_GET_TOP_RATED_MOVIES?api_key=$MOVIES_API_KEY&language=en-US&page=1""")

                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"

                connection.readTimeout = 15 * 1000

                connection.doInput = true
                connection.doOutput = false

                connection.connect()

                reader = BufferedReader(InputStreamReader(connection.inputStream))
                stringBuilder = StringBuilder()

                for (line in reader.readLines()) {
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("TopRatedMovies", responseString)

                val movieListResponse =
                    Gson().fromJson(responseString, MovieListResponse::class.java)

                return movieListResponse

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("Errors", "" + e.message ?: "")
            } finally {
                if (reader != null) {
                    try {
                        reader.close()
                    } catch (ioe: Exception) {
                        ioe.printStackTrace()
                    }
                }
            }
            return null
        }

    }

    class GetGenresTask() : AsyncTask<Void, Void, MovieListResponse>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val url: URL
            var reader: BufferedReader? = null
            val stringBuilder: StringBuilder

            try {
                url =
                    URL(""" $BASE_URL$API_GET_GENRE?api_key=$MOVIES_API_KEY&language=en-US&page=1""")

                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"

                connection.readTimeout = 15 * 1000

                connection.doInput = true
                connection.doOutput = false

                connection.connect()

                reader = BufferedReader(InputStreamReader(connection.inputStream))
                stringBuilder = StringBuilder()

                for (line in reader.readLines()) {
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("TopRatedMovies", responseString)

                val movieListResponse =
                    Gson().fromJson(responseString, MovieListResponse::class.java)

                return movieListResponse

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("Errors", "" + e.message ?: "")
            } finally {
                if (reader != null) {
                    try {
                        reader.close()
                    } catch (ioe: Exception) {
                        ioe.printStackTrace()
                    }
                }
            }
            return null
        }

    }

    class GetMoviesByGenreTask() : AsyncTask<Void, Void, MovieListResponse>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val url: URL
            var reader: BufferedReader? = null
            val stringBuilder: StringBuilder

            try {
                url =
                    URL(""" $BASE_URL$API_GET_MOVIES_BY_GENRE?api_key=$MOVIES_API_KEY&language=en-US&page=1""")

                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"

                connection.readTimeout = 15 * 1000

                connection.doInput = true
                connection.doOutput = false

                connection.connect()

                reader = BufferedReader(InputStreamReader(connection.inputStream))
                stringBuilder = StringBuilder()

                for (line in reader.readLines()) {
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("TopRatedMovies", responseString)

                val movieListResponse =
                    Gson().fromJson(responseString, MovieListResponse::class.java)

                return movieListResponse

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("Errors", "" + e.message ?: "")
            } finally {
                if (reader != null) {
                    try {
                        reader.close()
                    } catch (ioe: Exception) {
                        ioe.printStackTrace()
                    }
                }
            }
            return null
        }

    }

    class GetMovieDetailTask() : AsyncTask<Void, Void, MovieListResponse>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val url: URL
            var reader: BufferedReader? = null
            val stringBuilder: StringBuilder

            try {
                url =
                    URL(""" $BASE_URL$API_GET_MOVIE_DETAILS?api_key=$MOVIES_API_KEY&language=en-US&page=1""")

                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"

                connection.readTimeout = 15 * 1000

                connection.doInput = true
                connection.doOutput = false

                connection.connect()

                reader = BufferedReader(InputStreamReader(connection.inputStream))
                stringBuilder = StringBuilder()

                for (line in reader.readLines()) {
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("TopRatedMovies", responseString)

                val movieListResponse =
                    Gson().fromJson(responseString, MovieListResponse::class.java)

                return movieListResponse

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("Errors", "" + e.message ?: "")
            } finally {
                if (reader != null) {
                    try {
                        reader.close()
                    } catch (ioe: Exception) {
                        ioe.printStackTrace()
                    }
                }
            }
            return null
        }


    }

    class GetCreditsByMovieTask() : AsyncTask<Void, Void, GetCreditsByMovieResponse>() {
        override fun doInBackground(vararg p0: Void?): GetCreditsByMovieResponse? {
            val url: URL
            var reader: BufferedReader? = null
            val stringBuilder: StringBuilder

            try {
                url =
                    URL(""" $BASE_URL$API_GET_CREDITS_BY_MOVIE?api_key=$MOVIES_API_KEY&language=en-US&page=1""")

                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"

                connection.readTimeout = 15 * 1000

                connection.doInput = true
                connection.doOutput = false

                connection.connect()

                reader = BufferedReader(InputStreamReader(connection.inputStream))
                stringBuilder = StringBuilder()

                for (line in reader.readLines()) {
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("GetCreditsByMOvie", responseString)

                val movieListResponse =
                    Gson().fromJson(responseString, GetCreditsByMovieResponse::class.java)

                return movieListResponse

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("Errors", "" + e.message ?: "")
            } finally {
                if (reader != null) {
                    try {
                        reader.close()
                    } catch (ioe: Exception) {
                        ioe.printStackTrace()
                    }
                }
            }
            return null
        }


    }
}