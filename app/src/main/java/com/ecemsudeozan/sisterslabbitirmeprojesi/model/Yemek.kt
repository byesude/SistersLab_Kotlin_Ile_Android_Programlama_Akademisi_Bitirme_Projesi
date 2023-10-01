package com.ecemsudeozan.sisterslabbitirmeprojesi.model

data class Yemek(
    val message: String,
    val recipes: List<Recipe>,
    val status: Int
)