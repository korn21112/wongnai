package com.example.wongnai.data.api

import com.example.wongnai.data.Coins
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val BASE_URL = "https://api.coinranking.com/v1/public/"

interface ApiService {

    @GET("coins")
    fun getCoins(): Call<Coins>

    companion object{

        operator  fun invoke():ApiService{
            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)
        }
    }
}