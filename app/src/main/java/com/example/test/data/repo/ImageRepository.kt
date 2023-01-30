package com.example.test.data.repo

import com.example.test.data.network.WebSearchApi
import com.example.test.domain.entity.Image

interface ImageRepository {
    suspend fun searchImages(): List<Image>
}

class NetworkImageRepository : ImageRepository {
    override suspend fun searchImages() = WebSearchApi.retrofitService
        .getSearchImages("funny cat", 50)
        .value
        .map { Image(it.url, it.width, it.height) }
}
