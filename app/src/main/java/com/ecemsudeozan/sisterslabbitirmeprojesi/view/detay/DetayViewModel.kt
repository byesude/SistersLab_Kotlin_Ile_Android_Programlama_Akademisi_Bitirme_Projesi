package com.ecemsudeozan.sisterslabbitirmeprojesi.view.detay

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.CRUD
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.RecipeX
import com.ecemsudeozan.sisterslabbitirmeprojesi.repository.RecipeRepository

class DetayViewModel : ViewModel() {

    private val recipeRepository = RecipeRepository()
    val detaylarLiveData = MutableLiveData<RecipeX>()
    val guncellemeLiveData = MutableLiveData<CRUD>()


    fun detaylar(id: Int) {
        recipeRepository.detaylar(detaylarLiveData, id)
    }

    fun guncelleme(recipeX: RecipeX) {
        recipeRepository.guncelleme(guncellemeLiveData, recipeX)
    }

}