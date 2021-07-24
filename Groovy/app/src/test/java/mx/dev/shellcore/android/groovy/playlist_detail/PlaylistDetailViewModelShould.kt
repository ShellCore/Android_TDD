package mx.dev.shellcore.android.groovy.playlist_detail

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import mx.dev.shellcore.android.groovy.utils.BaseUnitTest
import mx.dev.shellcore.android.groovy.utils.getValueForTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import java.lang.RuntimeException

class PlaylistDetailViewModelShould : BaseUnitTest() {

    lateinit var viewModel: PlaylistDetailViewModel
    private val id = "1"
    private val service: PlaylistDetailService = mock(PlaylistDetailService::class.java)
    private val playlistDetail: PlaylistDetail = mock(PlaylistDetail::class.java)
    private val expected = Result.success(playlistDetail)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistDetailFromService() = runBlockingTest {
        viewModel = mockSuccesfulCase()

        viewModel.playlistDetail.getValueForTest()

        verify(service, times(1)).fetchPlaylistDetail(id)
    }

    @Test
    fun emitsPlaylistDetailFromService() = runBlockingTest {
        val viewModel = mockSuccesfulCase()

        assertEquals(expected, viewModel.playlistDetail.getValueForTest())
    }

    @Test
    fun emitsErrorWhenServiceFails() = runBlockingTest {
        val viewModel = mockFailureCase()
        assertEquals(exception, viewModel.playlistDetail.getValueForTest()!!.exceptionOrNull())
    }

    private suspend fun mockSuccesfulCase(): PlaylistDetailViewModel {
        `when`(service.fetchPlaylistDetail(id)).thenReturn(
            flow { emit(expected) }
        )

        val viewModel = PlaylistDetailViewModel(service)
        viewModel.getPlaylistDetail(id)
        return viewModel
    }

    private suspend fun mockFailureCase() : PlaylistDetailViewModel {
        `when`(service.fetchPlaylistDetail(id)).thenReturn(
            flow { emit(Result.failure<PlaylistDetail>(exception)) }
        )

        val viewModel = PlaylistDetailViewModel(service)
        viewModel.getPlaylistDetail(id)
        return viewModel
    }
}