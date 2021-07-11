package mx.dev.shellcore.android.groovy.playlist

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import mx.dev.shellcore.android.groovy.utils.BaseUnitTest
import mx.dev.shellcore.android.groovy.utils.captureValues
import mx.dev.shellcore.android.groovy.utils.getValueForTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*

class PlaylistViewModelShould: BaseUnitTest() {

    private val repository: PlaylistRepository = mock(PlaylistRepository::class.java)
    private val playlists: List<Playlist> = mock(List::class.java) as List<Playlist>
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistsFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulCase()
        viewModel.playlist.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulCase()
        assertEquals(expected, viewModel.playlist.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError() {
        val viewModel = mockFailureCase()
        assertEquals(exception, viewModel.playlist.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun showProgressbarWhileLoading() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.playlist.getValueForTest()

            assertEquals(true, values[0])
        }
    }

    @Test
    fun closeLoaderAfterPlaylistsLoad() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.playlist.getValueForTest()

            assertEquals(false, values.last())
        }
    }

    @Test
    fun closeLoaderAfterError() = runBlockingTest {
        val viewModel = mockFailureCase()

        viewModel.loader.captureValues {
            viewModel.playlist.getValueForTest()

            assertEquals(false, values.last())
        }
    }

    private fun mockSuccessfulCase(): PlaylistViewModel {
        runBlocking {
            `when`(repository.getPlaylists()).thenReturn(
                flow { emit(expected) }
            )
        }
        return PlaylistViewModel(repository)
    }

    private fun mockFailureCase(): PlaylistViewModel {
        runBlocking {
            `when`(repository.getPlaylists()).thenReturn(
                flow { emit(Result.failure<List<Playlist>>(exception)) }
            )
        }
        return PlaylistViewModel(repository)
    }
}