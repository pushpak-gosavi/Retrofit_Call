package com.pushpak.retrofitcall.viewmodel

import com.pushpak.retrofitcall.model.Articles
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any


@RunWith(JUnit4::class)
class MainViewModelTest {


    @Mock
    lateinit var mainrepository: Mainrepository

    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(mainrepository)
    }

    @Test (expected = IllegalStateException::class)
    fun `when call getNews() method`(){
        val articles = Articles(
            status = "NA",
            totalResults = 30,
            articles = emptyList()
        )
        runBlocking {
            Mockito.`when`(mainrepository.getTopHedLines(countryName = anyString(), apiKey = anyString()))
                .thenReturn(articles)

            val getData = viewModel.news("any", apiKey = "any")

            assertEquals(articles,getData)
        }


    }
}