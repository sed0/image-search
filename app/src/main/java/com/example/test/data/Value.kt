package com.example.test.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Value(
    @SerialName("url") val url: String,
    @SerialName("height") val height: Int,
    @SerialName("width") val width: Int,
    @SerialName("thumbnail") val thumbnail: String,
    @SerialName("thumbnailHeight") val thumbnailHeight: Int,
    @SerialName("thumbnailWidth") val thumbnailWidth: Int,
    @SerialName("base64Encoding") val base64Encoding: String? = null,
    @SerialName("name") val name: String,
    @SerialName("title") val title: String,
    @SerialName("provider") val provider: Provider,
    @SerialName("imageWebSearchUrl") val imageWebSearchUrl: String,
    @SerialName("webpageUrl") val webpageUrl: String,
)
