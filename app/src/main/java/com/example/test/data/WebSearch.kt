package com.example.test.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WebSearch(
    @SerialName("_type") val type: String,
    @SerialName("totalCount") val totalCount: Int,
    @SerialName("value") val value: List<Value>,
)
