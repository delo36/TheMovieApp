package com.padc.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.themovieapp.data.vos.MovieVO
import com.padc.themovieapp.delegates.BannerViewHolderDelegate
import com.padc.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_item_banner.view.*

class BannerViewHolder(itemView: View,private val mDelegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private  var mMovieVO : MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let {movie->
                mDelegate.onTapMovieFromBanner(movie.id)
            }
        }
    }

    fun bindData(movieVO: MovieVO) {
        mMovieVO = movieVO
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movieVO.posterPath}")
            .into(itemView.ivBannerImage)

        itemView.tvBannerMovieName.text = movieVO.title
    }

}