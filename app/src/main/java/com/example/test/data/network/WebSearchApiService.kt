package com.example.test.data.network

import com.example.test.data.WebSearch
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/"

@OptIn(ExperimentalSerializationApi::class)
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()

interface WebSearchApiService {
    @Headers(
        "X-RapidAPI-Key: 51056fb62emsh80ecc2bc5c12ad1p1e925cjsnba926c994e38",
        "X-RapidAPI-Host: contextualwebsearch-websearch-v1.p.rapidapi.com",
    )
    @GET("Search/ImageSearchAPI")
    suspend fun getSearchImages(
        @Query("q") search: String,
        @Query("pageSize") pageSize: Int,
    ): WebSearch
}

object WebSearchApi {
    val retrofitService: WebSearchApiService = retrofit.create(WebSearchApiService::class.java)
}
