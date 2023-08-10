package com.app.composemovies.data.models

import com.google.gson.annotations.SerializedName

data class MovieActorResponse(
    val cast: List<CastResult>,
    val id: Int
)

data class CastResult(
    val id: Int,
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String,
    val character: String,
    val order: Int
)


