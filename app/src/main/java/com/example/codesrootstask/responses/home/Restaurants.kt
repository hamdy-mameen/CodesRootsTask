package com.example.codesrootstask.responses.home

data class Restaurants(
    val IsOpen: String,
    val RestauranthId: Int,
    val cover: String,
    val cuisines: List<Cuisine>,
    val delivery_cost: Int,
    val delivery_time: Int,
    val logo: String,
    val name: String,
    val rate: Any
)