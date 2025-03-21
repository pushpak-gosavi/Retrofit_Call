package com.pushpak.retrofitcall.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pushpak.retrofitcall.api.APICall
import com.pushpak.retrofitcall.api.APIExecuter
import com.pushpak.retrofitcall.common.Resources
import com.pushpak.retrofitcall.model.Article
import com.pushpak.retrofitcall.model.Articles
import com.pushpak.retrofitcall.model.data.NewsDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val manRepository:Mainrepository
): ViewModel() {

    private val _state= mutableStateOf(NewsDataState())
    val newMutable = mutableIntStateOf(0)
    val state:MutableState<NewsDataState> = _state
     init {
         getNews()
     }

    fun increase(){
        newMutable.value ++
    }


    fun news(country:String , apiKey:String) : Flow<Resources<Articles>> = flow{
        try{
            emit(Resources.Loading())
            val news= manRepository.getTopHedLines(countryName = country, apiKey = apiKey)
            emit(Resources.Success(news))
        }catch (ex:HttpException){
            emit(Resources.Error(message = ex.message?:"Unexpected Error Ocurred"))
        }catch (e: IOException){
            emit(Resources.Error(message = "Couldn't reach server, Please check the internet"))
        }
    }

    fun getNews(){
            news(country = "us", apiKey = "7753f88a765e488792f2aaddb790703b").onEach {result->
                when(result){
                    is Resources.Loading -> {
                      _state.value =  NewsDataState(isLoading = true)

                    }

                    is Resources.Error -> {
                      _state.value=  NewsDataState(error = result.data?.status ?: "Unexpected error Occured")
                    }
                    is Resources.Success -> {
                      _state.value =  NewsDataState(data = result.data)
                    }
                }

            }.launchIn(viewModelScope)
    }
}