package com.padc.themovieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.themovieapp.adapters.MovieAdapter
import com.padc.themovieapp.data.vos.MovieVO
import com.padc.themovieapp.delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    lateinit var mMovieAdapter: MovieAdapter
    lateinit var mDelegate: MovieViewHolderDelegate

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setUpMovieListViewPod(delegate: MovieViewHolderDelegate){
        setDelegate(delegate)
        setUpRecyclerView()
    }

    private fun setDelegate(delegate: MovieViewHolderDelegate){
        this.mDelegate = delegate
    }

    private fun setUpRecyclerView() {
        mMovieAdapter = MovieAdapter(mDelegate)
        rvMovieList.adapter = mMovieAdapter
        rvMovieList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setData(movieList: List<MovieVO>){
        mMovieAdapter.setNewData(movieList)
    }
}