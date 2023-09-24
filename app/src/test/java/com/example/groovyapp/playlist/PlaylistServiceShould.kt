package com.example.groovyapp.playlist

import com.example.groovyapp.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class PlaylistServiceShould : BaseUnitTest() {

    private val api: PlaylistApi = mock()
    private lateinit var service: PlaylistService
    private val playlist: List<PlaylistModel> = mock()

    @Test
    fun fetchPlaylistFromApi() = runTest {
        service = PlaylistService(api)
        service.fetchPlaylists().first()

        verify(api, times(1)).fetchAllPlaylists()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem() = runTest {
        mockSuccessfulCase()
        assertEquals(Result.success(playlist), service.fetchPlaylists().first())
    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runTest {
        mockErrorCase()
        assertEquals(
            "Something went wrong",
            service.fetchPlaylists().first().exceptionOrNull()?.message
        )
    }

    private suspend fun mockSuccessfulCase() {
        `when`(api.fetchAllPlaylists()).thenReturn(playlist)
        service = PlaylistService(api)
    }

    private suspend fun mockErrorCase() {
        `when`(api.fetchAllPlaylists()).thenThrow(RuntimeException("Damn backend developers"))
        service = PlaylistService(api)
    }
}