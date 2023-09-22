package com.example.groovyapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlaylistViewModel(
    private val repository: PlaylistRepository
) : ViewModel() {

    private val _playlists: MutableStateFlow<List<PlaylistModel>?> =
        MutableStateFlow(null)
    val playlists: StateFlow<List<PlaylistModel>?> = _playlists


    init {
        repository.getPlaylist()
    }

    fun updatePlaylists(playlists: List<PlaylistModel>?) {
        _playlists.value = playlists
    }
}