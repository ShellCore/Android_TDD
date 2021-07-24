package mx.dev.shellcore.android.groovy.playlist_detail

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import mx.dev.shellcore.android.groovy.playlist.PlaylistApi
import mx.dev.shellcore.android.groovy.utils.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*

class PlaylistDetailServiceShould: BaseUnitTest() {

    private lateinit var service: PlaylistDetailService
    private val api: PlaylistApi = mock(PlaylistApi::class.java)
    private val id = "1"
    private val playlistDetail = mock(PlaylistDetail::class.java)

    @Test
    fun fetchPlaylistDetailsFromAPI() = runBlockingTest {
        service = PlaylistDetailService(api)

        service.fetchPlaylistDetail(id).single()
        verify(api, times(1)).fetchPlaylistById(id)
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem() = runBlockingTest {
        mockSuccessfulCase()
        assertEquals(Result.success(playlistDetail), service.fetchPlaylistDetail(id).single())
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runBlockingTest {
        mockFailureCase()
        assertEquals("Something went wrong", service.fetchPlaylistDetail(id).first().exceptionOrNull()?.message)
    }

    private suspend fun mockSuccessfulCase() {
        `when`(api.fetchPlaylistById(id)).thenReturn(playlistDetail)

        service = PlaylistDetailService(api)
    }

    private suspend fun mockFailureCase() {
        `when`(api.fetchPlaylistById(id)).thenThrow(RuntimeException("Damn backend developers"))

        service = PlaylistDetailService(api)
    }
}