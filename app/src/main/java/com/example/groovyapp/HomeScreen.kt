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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.groovyapp.playlist.PlaylistViewModel

@Composable
fun HomeScreen(
    homeViewModel: PlaylistViewModel = hiltViewModel()
) {
    val playlist = homeViewModel.playlists.collectAsState().value

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
            contentDescription = "listOfImage$id",
            //modifier = Modifier.testTag("listOfImage")
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(modifier = Modifier.testTag("playlistItem$id"), text = name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(modifier = Modifier.testTag("playlistSubItem$id"), text = category)
        }
    }
}