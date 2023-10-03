package com.example.groovyapp.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val repository: PlaylistRepository
) : ViewModel() {

    private val _playlists: MutableStateFlow<Result<List<PlaylistModel>>?> =
        MutableStateFlow(null)
    val playlists: StateFlow<Result<List<PlaylistModel>>?> = _playlists

    private val _loader: MutableStateFlow<Boolean> =
        MutableStateFlow(true)
    var loader: StateFlow<Boolean> = _loader

    init {
        viewModelScope.launch {
            repository.getPlaylist().collect {
                _loader.value = false
                _playlists.value = it
            }
        }

    }

}