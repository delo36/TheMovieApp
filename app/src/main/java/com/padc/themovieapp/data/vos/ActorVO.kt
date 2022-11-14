package com.padc.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class  ActorVO(
    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("id")
    val id: Int,

    @SerializedName("known_for")
    val knowFor: List<MovieVO>?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("profile_path")
    val profilePath: String?,

    @SerializedName("gender")
    val gender: Int?,

    @SerializedName("known_for_department")
    val knowForDepartment: String?,

    @SerializedName("original_name")
    val originalName: String?,

    @SerializedName("cast_id")
    val castId: Int?,

    @SerializedName("character")
    val character: String?,

    @SerializedName("credit_id")
    val creditId: String?,

    @SerializedName("order")
    val order: Int?,

    @SerializedName("department")
    val department: String?,

    @SerializedName("job")
    val job: String?,
    )