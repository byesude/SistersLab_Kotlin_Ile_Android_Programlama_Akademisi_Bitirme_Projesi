package com.ecemsudeozan.sisterslabbitirmeprojesi.view.ekle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.CRUD
import com.ecemsudeozan.sisterslabbitirmeprojesi.model.EkleIstek
import com.ecemsudeozan.sisterslabbitirmeprojesi.repository.RecipeRepository

class EkleViewModel : ViewModel() {
    private val recipeRepository = RecipeRepository()
    val eklemeLiveData = MutableLiveData<CRUD>()

    fun ekleme(ekleIstek: EkleIstek) {
        recipeRepository.ekleme(eklemeLiveData, ekleIstek)
    }
}