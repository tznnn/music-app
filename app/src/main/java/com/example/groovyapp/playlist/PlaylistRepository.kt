package com.example.groovyapp.playlist

import kotlinx.coroutines.flow.Flow

class PlaylistRepository(
    private val service: PlaylistService
) {
    suspend fun getPlaylist(): Flow<Result<List<PlaylistModel>>> =
        service.fetchPlaylists()

}