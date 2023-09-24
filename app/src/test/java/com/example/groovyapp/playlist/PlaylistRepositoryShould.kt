package com.example.groovyapp.playlist

import com.example.groovyapp.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class PlaylistRepositoryShould : BaseUnitTest() {

    private val exception = RuntimeException("Something went wrong")
    private val service: PlaylistService = mock()
    private val playlist = mock<List<PlaylistModel>>()

    @Test
    fun getPlaylistsFromService() = runTest {
        val repo = PlaylistRepository(service)
        repo.getPlaylist()

        verify(service, times(1)).fetchPlaylists()
    }

    @Test
    fun emitPlaylistsFromService() = runTest {
        val repository = mockSuccessfulCase()
        assertEquals(playlist, repository.getPlaylist().first().getOrNull())
    }

    @Test
    fun propagateErrors() = runTest {
        val repository = mockFailureCase()
        assertEquals(exception, repository.getPlaylist().first().exceptionOrNull())
    }

    private suspend fun mockSuccessfulCase(): PlaylistRepository {
        `when`(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.success(playlist))
            }
        )
        return PlaylistRepository(service)
    }

    private suspend fun mockFailureCase(): PlaylistRepository {
        `when`(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.failure(exception = exception))
            }
        )
        return PlaylistRepository(service)
    }
}