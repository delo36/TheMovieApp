package com.padc.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padc.themovieapp.data.vos.ActorVO

data class GetActorResponse (
    @SerializedName("results")
    val results:List<ActorVO>?
)