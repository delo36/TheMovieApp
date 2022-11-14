package com.padc.themovieapp.viewpods

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.themovieapp.adapters.ActorAdapter
import com.padc.themovieapp.data.vos.ActorVO
import kotlinx.android.synthetic.main.view_pod_actor_list.view.*

class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mActorAdapter: ActorAdapter

    override fun onFinishInflate() {
        setUpActorRecyclerView()
        super.onFinishInflate()
    }

    fun setUpActorViewPod(backgroundColorReference: Int, titleText: String, moreTitleText: String) {
        setBackgroundColor(ContextCompat.getColor(context, backgroundColorReference))
        tvBestActors.text = titleText
        tvMoreActors.text = moreTitleText
        tvMoreActors.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    fun setData(actors: List<ActorVO>){
        mActorAdapter.setNewData(actors)
    }

    private fun setUpActorRecyclerView() {
        mActorAdapter = ActorAdapter()
        rvBestActors.adapter = mActorAdapter
        rvBestActors.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}