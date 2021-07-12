package mx.dev.shellcore.android.groovy.playlist

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import mx.dev.shellcore.android.groovy.utils.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import java.lang.RuntimeException

class PlaylistServiceShould : BaseUnitTest() {

    private lateinit var service: PlaylistService
    private val api: PlaylistApi = mock(PlaylistApi::class.java)
    private val playlists: List<PlaylistRaw> = mock(List::class.java) as List<PlaylistRaw>

    @Test
    fun fetchPlaylistFromApi() = runBlockingTest {
        service = PlaylistService(api)
        service.fetchPlaylists().first()
        verify(api, times(1)).fetchAllPlaylists()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem() = runBlockingTest {
        mockSuccesfulCase()
        assertEquals(Result.success(playlists), service.fetchPlaylists().first())
    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runBlockingTest {
        mockFailureCase()
        assertEquals(
            "Something went wrong",
            service.fetchPlaylists().first().exceptionOrNull()?.message
        )
    }

    private suspend fun mockSuccesfulCase() {
        `when`(api.fetchAllPlaylists()).thenReturn(
            playlists
        )

        service = PlaylistService(api)
    }

    private suspend fun mockFailureCase() {
        `when`(api.fetchAllPlaylists()).thenThrow(RuntimeException("Damn backend developers"))

        service = PlaylistService(api)
    }
}