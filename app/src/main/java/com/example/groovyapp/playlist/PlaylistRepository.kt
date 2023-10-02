package com.example.groovyapp.playlist

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val service: PlaylistService
) {
    suspend fun getPlaylist(): Flow<Result<List<PlaylistModel>>> =
        service.fetchPlaylists()

}