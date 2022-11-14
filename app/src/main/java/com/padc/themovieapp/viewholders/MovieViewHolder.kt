package com.padc.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.themovieapp.data.vos.MovieVO
import com.padc.themovieapp.delegates.MovieViewHolderDelegate
import com.padc.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import kotlinx.android.synthetic.main.view_item_banner.view.*

class MovieViewHolder(itemView: View,private val mDelegate : MovieViewHolderDelegate): RecyclerView.ViewHolder(itemView) {
    private  var mMovieVO : MovieVO? = null

    fun bindData(movieVO: MovieVO) {
        mMovieVO = movieVO
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movieVO.posterPath}")
            .into(itemView.ivMovieImage)

        itemView.tvMovieName.text = movieVO.title
        itemView.tvMovieRating.text = movieVO.voteAverage?.toString()
        itemView.rbMovieRating.rating = movieVO.getRatingBasedOnFiveStar()!!
    }

    init {
        itemView.setOnClickListener {
          mMovieVO?.let {movie->
              mDelegate.onTapMovie(movie.id)
          }
        }
    }
}