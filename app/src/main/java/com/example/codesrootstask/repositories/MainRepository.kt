package com.example.codesrootstask.repositories

import com.example.codesrootstask.data.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    val api:ApiService
) {
    suspend fun getAds() = api.getAds()
    suspend fun getCategories() = api.getCategories()
    suspend fun getMainHome() =api.getHomePage()

}