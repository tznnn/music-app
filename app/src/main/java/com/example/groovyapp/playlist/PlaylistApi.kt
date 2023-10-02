package com.example.groovyapp.playlist

import retrofit2.http.GET

interface PlaylistApi {

    @GET("playlists")
    suspend fun fetchAllPlaylists(): List<PlaylistModel>
}