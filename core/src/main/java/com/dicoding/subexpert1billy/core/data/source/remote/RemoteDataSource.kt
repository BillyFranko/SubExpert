package com.dicoding.subexpert1billy.core.data.source.remote

import android.util.Log
import com.dicoding.subexpert1billy.core.data.source.remote.network.ApiResponse
import com.dicoding.subexpert1billy.core.data.source.remote.network.ApiService
import com.dicoding.subexpert1billy.core.data.source.remote.response.MealsItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllFoods(
        ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<ApiResponse<List<MealsItem?>?>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.meals
                if (dataArray?.isNotEmpty() == true){
                    emit(ApiResponse.Success(dataArray))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                Log.e("Remote Data Source", e.toString())
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(ioDispatcher)
    }
}