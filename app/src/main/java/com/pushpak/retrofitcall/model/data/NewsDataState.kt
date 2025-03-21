package com.pushpak.retrofitcall.model.data

import com.pushpak.retrofitcall.model.Articles
import java.lang.Error

data class NewsDataState(
    val isLoading:Boolean = false,
    val data:Articles? = null,
    val error: String = ""
)
