package mx.dev.shellcore.android.groovy.playlist_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PlaylistDetailViewModel(private val service: PlaylistDetailService) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val playlistDetail: MutableLiveData<Result<PlaylistDetail>> = MutableLiveData()

    fun getPlaylistDetail(id: String) {
        loader.postValue(true)
        viewModelScope.launch {
            service.fetchPlaylistDetail(id)
                .onEach {
                    loader.postValue(false)
                }
                .collect {
                    playlistDetail.postValue(it)
                }
        }
    }
}