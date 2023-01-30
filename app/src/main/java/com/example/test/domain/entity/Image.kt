package com.example.test.domain.entity

import androidx.annotation.Px

data class Image(
    val uri: String,
    @Px val width: Int,
    @Px val height: Int,
)
