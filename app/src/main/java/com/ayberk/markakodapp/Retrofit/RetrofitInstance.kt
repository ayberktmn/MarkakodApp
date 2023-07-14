package com.ayberk.markakodapp.Retrofit


import com.ayberk.markakodapp.Models.Base
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInstance {
    @GET("/todos/1")
    fun getData() : Call<Base>
}