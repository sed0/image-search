package com.example.test.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Provider(
    @SerialName("name") val name: String,
    @SerialName("favIcon") val favIcon: String,
    @SerialName("favIconBase64Encoding") val favIconBase64Encoding: String? = null,
)
