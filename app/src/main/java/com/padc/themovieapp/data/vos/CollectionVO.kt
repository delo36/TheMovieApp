package com.padc.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class CollectionVO (
    @SerializedName("id")
    val id: Int,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,
)