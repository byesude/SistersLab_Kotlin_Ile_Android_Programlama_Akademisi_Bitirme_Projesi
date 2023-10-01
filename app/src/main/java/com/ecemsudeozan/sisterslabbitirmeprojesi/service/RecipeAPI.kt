package com.ecemsudeozan.sisterslabbitirmeprojesi.service

import com.ecemsudeozan.sisterslabbitirmeprojesi.model.CRUD
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.DetayModel
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.EkleIstek
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.RecipeX
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.Yemek
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RecipeAPI {

    //https://api.canerture.com/recipes/get_recipes.php
    @GET("get_recipes.php")
    fun tumTarifler(): Call<Yemek>

    //https://api.canerture.com/recipes/get_recipe_detail.php
    @GET("get_recipe_detail.php")
    fun detaylar(
        @Query("id") id: Int
    ): Call<DetayModel>

    //https://api.canerture.com/recipes/update_recipe.php
    @POST("update_recipe.php")
    fun guncelle(
        @Body recipex: RecipeX
    ): Call<CRUD>

    //https://api.canerture.com/recipes/add_recipe.php
    @POST("add_recipe.php")
    fun ekle(
        @Body ekleIstek: EkleIstek
    ): Call<CRUD>

    //https://api.canerture.com/recipes/search_recipe.php?query=mercimek
    @GET("search_recipe.php")
    fun ara(
        @Query("query") query: String
    ): Call<Yemek>
}