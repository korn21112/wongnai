package com.example.wongnai.data

data class Coins(
    val status: String,
    val data: Data
)

data class Data(
    val coins: List<Coin>
)

data class Coin(
    val id: String,
    val uuid: String,
    val slug: String,
    val symbol: String,
    val name: String,
    val description: String,
    val color: String,
    val iconType: String,
    val iconUrl: String,
    val websiteUrl: String
)
