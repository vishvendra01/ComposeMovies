package com.app.composemovies.domain.models

data class Cast(
    val id: Int,
    val name: String,
    val profilePath: String,
    val character: String,
    val order: Int
)