package com.example.codesrootstask.responses.home

data class HomeResponse(
    val GetFreeDliveryBranches: GetFreeDliveryBranches,
    val GetNearestBranche: GetNearestBranche,
    val GetPercentageForVendors: GetPercentageForVendors,
    val MostSellItems: MostSellItems,
    val getMostOrderedBranch: GetMostOrderedBranch,
    val lastoffers: Lastoffers
)