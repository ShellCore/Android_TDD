package mx.dev.shellcore.android.groovy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlaylistViewModel(private val repository: PlaylistRepository): ViewModel() {

    val playlist = MutableLiveData<List<Playlist>>()

    init {
        viewModelScope.launch {
            repository.getPlaylists()
                .collect {
                    playlist.value = it
                }
        }
    }
}
