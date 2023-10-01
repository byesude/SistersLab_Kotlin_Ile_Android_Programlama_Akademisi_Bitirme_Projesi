package com.ecemsudeozan.sisterslabbitirmeprojesi.repository

import androidx.lifecycle.MutableLiveData
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.CRUD
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.DetayModel
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.EkleIstek
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.Recipe
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.RecipeX
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.Yemek
import com.ecemsudeozan.sisterslabbitirmeprojesi.retrofit.RetrofitModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeRepository {

    private val retrofitModule = RetrofitModule()

    //anasayfa
    fun tumTarifler(tumTarifler: MutableLiveData<List<Recipe>>) {
        //içinde oluşturduğumuz fonk erişiyoruz
        retrofitModule.retrofitBuilder.tumTarifler().enqueue(object : Callback<Yemek> {
            override fun onResponse(call: Call<Yemek>, response: Response<Yemek>) {
                //istek başarılı olursa
                if (response.isSuccessful) {
                    val tumTariflerListe = response.body()?.recipes
                    tumTarifler.value = tumTariflerListe
                } else {
                    tumTarifler.value = null
                }
            }

            override fun onFailure(call: Call<Yemek>, t: Throwable) {
                //istek başarısız olursa
            }

        })
    }

    //detay sayfası (detaylar ve güncelleme ekranı için)
    fun detaylar(detaylar: MutableLiveData<RecipeX>, id: Int) {
        retrofitModule.retrofitBuilder.detaylar(id).enqueue(object : Callback<DetayModel> {
            override fun onResponse(call: Call<DetayModel>, response: Response<DetayModel>) {
                if (response.isSuccessful) {
                    val detay = response.body()?.recipe
                    detaylar.value = detay
                } else {
                    detaylar.value = null
                }
            }

            override fun onFailure(call: Call<DetayModel>, t: Throwable) {

            }

        })
    }

    fun guncelleme(guncelleme: MutableLiveData<CRUD>, recipeX: RecipeX) {
        retrofitModule.retrofitBuilder.guncelle(recipeX).enqueue(object : Callback<CRUD> {
            override fun onResponse(call: Call<CRUD>, response: Response<CRUD>) {
                if (response.isSuccessful) {
                    val guncelle = response.body()
                    guncelleme.value = guncelle
                } else {
                    guncelleme.value = null
                }
            }

            override fun onFailure(call: Call<CRUD>, t: Throwable) {

            }

        })
    }

    //ekleme sayfası
    fun ekleme(ekleme: MutableLiveData<CRUD>, ekleIstek: EkleIstek) {
        retrofitModule.retrofitBuilder.ekle(ekleIstek).enqueue(object : Callback<CRUD> {
            override fun onResponse(call: Call<CRUD>, response: Response<CRUD>) {
                if (response.isSuccessful) {
                    val ekle = response.body()
                    ekleme.value = ekle
                } else {
                    ekleme.value = null
                }
            }

            override fun onFailure(call: Call<CRUD>, t: Throwable) {

            }

        })
    }

    //arama yapma
    fun arama(arama: MutableLiveData<List<Recipe>>, query: String) {
        retrofitModule.retrofitBuilder.ara(query).enqueue(object : Callback<Yemek> {
            override fun onResponse(call: Call<Yemek>, response: Response<Yemek>) {
                if (response.isSuccessful) {
                    val ara = response.body()?.recipes
                    arama.value = ara
                } else {
                    arama.value = null
                }
            }

            override fun onFailure(call: Call<Yemek>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}

