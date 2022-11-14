package com.padc.themovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.themovieapp.R
import com.padc.themovieapp.data.vos.ActorVO
import com.padc.themovieapp.viewholders.ActorViewHolder

class ActorAdapter:RecyclerView.Adapter<ActorViewHolder>() {

    private var mActors:List<ActorVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actors,parent,false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        if(mActors.isNotEmpty()){
            holder.blindData(mActors[position])
        }
    }

    override fun getItemCount(): Int {
       return mActors.count()
    }

    fun setNewData(actors: List<ActorVO>){
        mActors = actors
        notifyDataSetChanged()
    }
}