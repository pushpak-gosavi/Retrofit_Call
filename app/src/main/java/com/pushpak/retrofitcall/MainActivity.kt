package com.pushpak.retrofitcall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pushpak.retrofitcall.ui.theme.RetrofitCallTheme
import com.pushpak.retrofitcall.viewmodel.MainViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.Dispatchers

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitCallTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.data?.let {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.data.articles) { item ->
                    Text(text = item.title)
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if (state.error.isNotBlank()) {
            Text(color = MaterialTheme.colorScheme.error, text = state.error)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitCallTheme {
        Greeting("Android")
    }
}