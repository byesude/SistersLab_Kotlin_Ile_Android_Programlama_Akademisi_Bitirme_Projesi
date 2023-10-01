package com.ecemsudeozan.sisterslabbitirmeprojesi.view.anasayfa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.Recipe
import com.ecemsudeozan.sisterslabbitirmeprojesi.repository.RecipeRepository

class AnasayfaViewModel : ViewModel() {

    private val recipeRepository = RecipeRepository()
    val tumTariflerLiveData = MutableLiveData<List<Recipe>>()
    val aramaLiveData = MutableLiveData<List<Recipe>>()


    fun tumTarifler() {
        recipeRepository.tumTarifler(tumTariflerLiveData)
    }

    fun arama(query: String) {
        recipeRepository.arama(aramaLiveData, query)
    }

}