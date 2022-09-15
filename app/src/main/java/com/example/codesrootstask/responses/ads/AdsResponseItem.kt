package com.example.codesrootstask.responses.ads

data class AdsResponseItem(
    val AdsSpacesprice: List<AdsSpacesprice>,
    val created: String,
    val id: Int,
    val name: String
)