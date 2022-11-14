package com.padc.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.themovieapp.R
import com.padc.themovieapp.data.vos.MovieVO
import com.padc.themovieapp.delegates.BannerViewHolderDelegate
import com.padc.themovieapp.viewholders.BannerViewHolder

class BannerAdapter(private val mDelegate: BannerViewHolderDelegate) :
    RecyclerView.Adapter<BannerViewHolder>() {

    private var mMovieList : List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_item_banner, parent, false)
        return BannerViewHolder(view, mDelegate)

    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        if(mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return if(mMovieList.count() > 5 )
            5
        else
            mMovieList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList: List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}