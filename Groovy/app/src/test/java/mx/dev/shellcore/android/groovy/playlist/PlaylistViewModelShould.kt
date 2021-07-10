package mx.dev.shellcore.android.groovy.playlist

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import mx.dev.shellcore.android.groovy.utils.BaseUnitTest
import mx.dev.shellcore.android.groovy.utils.getValueForTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*

class PlaylistViewModelShould: BaseUnitTest() {

    private val repository: PlaylistRepository = mock(PlaylistRepository::class.java)
    private val playlists: List<Playlist> = mock(List::class.java) as List<Playlist>
    private val expected = Result.success(playlists)

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

    private fun mockSuccessfulCase(): PlaylistViewModel {
        runBlocking {
            `when`(repository.getPlaylists()).thenReturn(
                flow { emit(expected) }
            )
        }
        val viewModel = PlaylistViewModel(repository)
        return viewModel
    }
}