package com.example.groovyapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.groovyapp.utils.MainCoroutineScopeRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class PlaylistViewModelShould {

    @get:Rule
    var mainCoroutineScopeRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel: PlaylistViewModel

    private val repository: PlaylistRepository = mock()

    init {
        viewModel = PlaylistViewModel(repository = repository)
    }

    @Test
    fun getPlaylistsFromRepository() {
        viewModel.updatePlaylists(viewModel.playlists.value)
        verify(repository, times(1)).getPlaylist()
    }
}