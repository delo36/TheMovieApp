package com.padc.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.themovieapp.data.vos.ActorVO
import com.padc.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_actors.view.*

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun blindData(actor: ActorVO){
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${actor.profilePath}")
            .into(itemView.ivBestActor)

        itemView.tvActorName.text = actor.name
    }
}