package com.ecemsudeozan.sisterslabbitirmeprojesi.retrofit

import com.ecemsudeozan.sisterslabbitirmeprojesi.service.RecipeAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitModule {

    val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()
        .create(RecipeAPI::class.java)

    companion object {
        const val BASE_URL = "https://api.canerture.com/recipes/"
    }
}
