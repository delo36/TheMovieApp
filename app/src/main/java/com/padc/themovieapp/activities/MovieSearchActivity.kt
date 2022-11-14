package com.padc.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.jakewharton.rxbinding4.widget.textChanges
import com.padc.themovieapp.R
import com.padc.themovieapp.adapters.MovieAdapter
import com.padc.themovieapp.data.models.MovieModelImpl
import com.padc.themovieapp.delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.activity_movie_search.*
import java.util.concurrent.TimeUnit

class MovieSearchActivity : AppCompatActivity(), MovieViewHolderDelegate {

    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,MovieSearchActivity::class.java)
        }
    }

    private lateinit var mMovieAdapter : MovieAdapter

    private val mMovieModel = MovieModelImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)

        setupRecyclerView()
        setupListener()
    }

    private fun setupListener() {
        edtSearch.textChanges()
            .debounce (500L,TimeUnit.MILLISECONDS)
            .flatMap { mMovieModel.searchMovie(it.toString()) }
            .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
            .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe ({
                mMovieAdapter.setNewData(it)
            },{
                //Error
            })
    }

    private fun setupRecyclerView() {
        mMovieAdapter = MovieAdapter(this)
        rvMovies.adapter = mMovieAdapter
        rvMovies.layoutManager = GridLayoutManager(this,2)
    }

    override fun onTapMovie(movieId: Int) {
        TODO("Not yet implemented")
    }
}