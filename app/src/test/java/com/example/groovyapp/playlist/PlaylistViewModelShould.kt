package com.example.groovyapp.playlist

import com.example.groovyapp.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class PlaylistViewModelShould : BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<PlaylistModel>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistsFromRepository() = runTest {
        mockSuccessfulCase()
        verify(repository, times(1)).getPlaylist()
    }

    @Test
    fun emitsPlaylistsFromRepository() = runTest {
        val viewModel = mockSuccessfulCase()
        assertEquals(expected, viewModel.playlists.value)
    }

    @Test
    fun emitErrorWhenReceiveError() = runTest {
        val viewModel = mockErrorCase()
        assertEquals(exception, viewModel.playlists.value?.exceptionOrNull())
    }


    @Test
    fun closeIndicatorAfterPlaylistLoad() = runTest {
        val viewModel = mockSuccessfulCase()
        assertEquals(false, viewModel.loader.value)
    }

    @Test
    fun closeIndicatorAfterError() = runTest {
        val viewModel = mockErrorCase()
        assertEquals(false, viewModel.loader.value)
    }

    private fun mockErrorCase(): PlaylistViewModel {
        runTest {
            `when`(repository.getPlaylist()).thenReturn(
                flow {
                    emit(Result.failure(exception = exception))
                }
            )
        }
        return PlaylistViewModel(repository = repository)
    }

    private suspend fun mockSuccessfulCase(): PlaylistViewModel {
        runTest {
            `when`(repository.getPlaylist()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        return PlaylistViewModel(repository = repository)
    }

}