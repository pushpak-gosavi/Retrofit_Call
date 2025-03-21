package com.pushpak.retrofitcall.api

import com.pushpak.retrofitcall.viewmodel.Mainrepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIExecuter {

    @Provides
    @Singleton
    fun provideNewsAPI(): APICall {
      return  Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APICall::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: APICall): Mainrepository {
        return Mainrepository(api)
    }
}