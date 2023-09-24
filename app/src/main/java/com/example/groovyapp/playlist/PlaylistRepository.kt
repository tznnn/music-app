package com.example.groovyapp.playlist

import kotlinx.coroutines.flow.Flow

class PlaylistRepository(
) {
    suspend fun getPlaylist() : Flow<Result<List<PlaylistModel>>> {
        TODO("asdasda")
    }
}