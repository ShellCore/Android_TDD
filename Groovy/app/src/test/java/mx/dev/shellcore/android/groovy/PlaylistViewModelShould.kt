package mx.dev.shellcore.android.groovy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import mx.dev.shellcore.android.groovy.utils.MainCoroutineScopeRule
import mx.dev.shellcore.android.groovy.utils.getValueForTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class PlaylistViewModelShould {

    @get:Rule val coroutinesTestRule = MainCoroutineScopeRule()
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()


    private val repository: PlaylistRepository = mock(PlaylistRepository::class.java)
    private val playlists: List<Playlist> = mock(List::class.java) as List<Playlist>
    private val expected = Result.success(playlists)

    @Test
    fun getPlaylistsFromRepository() = runBlockingTest {
        runBlocking {
            `when`(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        val viewModel = PlaylistViewModel(repository)
        viewModel.playlist.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository() = runBlockingTest {
        runBlocking {
            `when`(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        val viewModel = PlaylistViewModel(repository)

        assertEquals(expected, viewModel.playlist.getValueForTest())
    }
}