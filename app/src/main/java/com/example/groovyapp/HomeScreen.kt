package com.example.groovyapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.groovyapp.playlist.PlaylistApi
import com.example.groovyapp.playlist.PlaylistModel
import com.example.groovyapp.playlist.PlaylistRepository
import com.example.groovyapp.playlist.PlaylistService
import com.example.groovyapp.playlist.PlaylistViewModel
import com.example.groovyapp.playlist.PlaylistViewModelFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun HomeScreen() {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.105:2999/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(PlaylistApi::class.java)
    val service = PlaylistService(api)
    val repository = PlaylistRepository(service)
    val viewModel: PlaylistViewModel = viewModel(factory = PlaylistViewModelFactory(repository))


    val playlist = viewModel.playlists.collectAsState().value
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text("Playlists") }, backgroundColor = MaterialTheme.colors.primary)
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn(
                modifier = Modifier.testTag("playlistLazyColumn"),
                content = {
                    items(playlist?.getOrNull() ?: emptyList()) { item ->
                        ListItem(
                            id = item.id,
                            name = item.name,
                            category = item.category,
                            image = R.drawable.ic_launcher_background
                        )

                    }
                })
        }
    })
}

@Composable
fun ListItem(
    name: String,
    category: String,
    image: Int,
    id: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "ListOfImage"
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(modifier = Modifier.testTag("playlistItem"), text = name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = category)
        }
    }
}