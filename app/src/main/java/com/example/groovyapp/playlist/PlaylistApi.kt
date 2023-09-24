package com.example.groovyapp.playlist

interface PlaylistApi {
    suspend fun fetchAllPlaylists(): List<PlaylistModel> {
        return listOf()
    }
}