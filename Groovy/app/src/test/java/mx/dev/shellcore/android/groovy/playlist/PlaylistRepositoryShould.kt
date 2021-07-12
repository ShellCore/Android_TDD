package mx.dev.shellcore.android.groovy.playlist

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import mx.dev.shellcore.android.groovy.utils.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*

class PlaylistRepositoryShould : BaseUnitTest() {

    private val service: PlaylistService = mock(PlaylistService::class.java)
    private val mapper: PlaylistMapper = mock(PlaylistMapper::class.java)
    private val playlists: List<Playlist> = mock(List::class.java) as List<Playlist>
    private val playlistsRaw : List<PlaylistRaw> = mock(List::class.java) as List<PlaylistRaw>
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getsPlaylistsFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()
        repository.getPlaylists()
        verify(service, times(1)).fetchPlaylists()
    }

    @Test
    fun emitMappedPlaylistsFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()
        assertEquals(playlists, repository.getPlaylists().first().getOrNull()!!)
    }

    @Test
    fun propagateErrors() = runBlockingTest {
        val repository = mockFailureCase()

        assertEquals(exception, repository.getPlaylists().first().exceptionOrNull()!!)
    }

    @Test
    fun delegateBussinessLogicToMapper() = runBlockingTest {
        val repository = mockSuccessfulCase()

        repository.getPlaylists().first()

        verify(mapper, times(1)).invoke(playlistsRaw)

    }

    private suspend fun mockSuccessfulCase(): PlaylistRepository {
        `when`(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.success(playlistsRaw))
            }
        )

        `when`(mapper.invoke(playlistsRaw)).thenReturn(playlists)

        return PlaylistRepository(service, mapper)
    }

    private suspend fun mockFailureCase(): PlaylistRepository {
        `when`(service.fetchPlaylists()).thenReturn(
            flow {
                emit(Result.failure<List<PlaylistRaw>>(exception))
            }
        )

        return PlaylistRepository(service, mapper)
    }
}