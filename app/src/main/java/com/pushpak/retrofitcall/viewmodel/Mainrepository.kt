package com.pushpak.retrofitcall.viewmodel

import com.pushpak.retrofitcall.api.APICall
import com.pushpak.retrofitcall.model.Article
import com.pushpak.retrofitcall.model.Articles
import javax.inject.Inject

class Mainrepository @Inject constructor(val apiCall: APICall) {

    suspend fun getTopHedLines(countryName:String, apiKey:String):Articles{
       return apiCall.getTopHedLines(country = countryName, apiKey = apiKey)
    }
}