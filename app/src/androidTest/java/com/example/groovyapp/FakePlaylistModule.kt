package com.example.groovyapp

import com.example.groovyapp.playlist.PlaylistApi
import com.example.groovyapp.playlist.PlaylistModule
import com.example.groovyapp.playlist.PlaylistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PlaylistModule::class]
)
object FakePlaylistModule {
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit)= retrofit.create(PlaylistApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl("http://192.168.1.101:2999/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}