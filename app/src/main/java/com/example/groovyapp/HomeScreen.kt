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

@Composable
fun HomeScreen() {

    val repository = PlaylistRepository()
    val viewModel: PlaylistViewModel = viewModel(factory = PlaylistViewModelFactory(repository))

    val myData = viewModel.playlists.collectAsState().value

    val playlist =
        listOf(
            PlaylistModel(1, "sfs", "sdfsdf", 3),
            PlaylistModel(2, "aa", "ddd", 3)
        )
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
                    items(playlist) { item ->
                        ListItem(
                            id = item.id,
                            name = item.name,
                            category = item.category,
                            image = item.image
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
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "ListOfImage"
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = category)
        }
    }
}