package com.dicoding.subexpert1billy.core.data.source.remote.network

import com.dicoding.subexpert1billy.core.data.source.remote.response.FoodsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.php")
    suspend fun getList(
        @Query("f") f : String = "b"
    ) : FoodsResponse

}