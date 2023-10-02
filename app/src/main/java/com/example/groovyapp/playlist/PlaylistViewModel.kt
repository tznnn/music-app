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


    init {
        viewModelScope.launch {
            repository.getPlaylist().collect {
                _playlists.value = it
            }
        }
    }

}