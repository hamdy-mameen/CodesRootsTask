package com.example.codesrootstask.data

import com.example.codesrootstask.responses.ads.AdsResponse
import com.example.codesrootstask.responses.category.CategoryResponse
import com.example.codesrootstask.responses.home.HomeResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("MobileMainPage/GetMainSliders")
    suspend fun getAds(
        @Header("lang") lang:String ="en",
        @Field("googleId") googleId:String ="ChIJ88rv8bI_WBQRkvVBLDeZQUg"
    ):Response<AdsResponse>

    @FormUrlEncoded
    @POST("MobileMainPage/GetHomePage")
    suspend fun getHomePage(
        @Header("lang") lang:String ="en",
        @Field("googleId") googleId:String ="ChIJ88rv8bI_WBQRkvVBLDeZQUg"
    ):Response<HomeResponse>

    @GET("Categories/index")
    suspend fun getCategories():Response<CategoryResponse>
}