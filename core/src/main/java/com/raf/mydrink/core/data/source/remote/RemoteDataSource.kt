package com.raf.mydrink.core.data.source.remote

import android.util.Log
import com.raf.mydrink.core.data.source.remote.network.ApiResponse
import com.raf.mydrink.core.data.source.remote.network.ApiService
import com.raf.mydrink.core.data.source.remote.response.DrinkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService){

    suspend fun getAllDrink(): Flow<ApiResponse<List<DrinkResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.drinks
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.drinks))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}