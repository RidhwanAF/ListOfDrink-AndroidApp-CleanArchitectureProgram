package com.raf.mydrink.core.data.source.remote.network

import com.raf.mydrink.core.data.source.remote.response.ListDrinkResponse
import retrofit2.http.GET

interface ApiService {
    @GET("search.php?f=a")
    suspend fun getList(): ListDrinkResponse
}