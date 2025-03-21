package com.pushpak.retrofitcall.model


import com.google.gson.annotations.SerializedName

data class Articles(
    val status:String,
    val totalResults:Int,
    val articles: List<Article>
)