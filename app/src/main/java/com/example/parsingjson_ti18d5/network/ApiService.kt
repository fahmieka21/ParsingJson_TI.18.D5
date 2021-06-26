package com.example.parsingjson_ti18d5.network

import com.example.parsingjson_ti18d5.model.ResponseUser
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //MENAMPILKAN USER DENGAN QUARY//
    @GET("api/users")
    fun getListUser(@Query("page") page: String): retrofit2.Call<ResponseUser>
}