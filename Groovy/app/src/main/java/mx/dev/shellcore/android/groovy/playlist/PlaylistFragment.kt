package mx.dev.shellcore.android.groovy.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import mx.dev.shellcore.android.groovy.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlaylistViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setupViewModel()

        viewModel.playlist
            .observe(this as LifecycleOwner, {playlist ->
                if (playlist.getOrNull() != null) {
                    setupList(view, playlist.getOrNull()!!)
                } else {
                    // TODO
                }
            })

        return view
    }

    private fun setupList(
        view: View?,
        playlist: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlaylistRecyclerViewAdapter(playlist)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PlaylistViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {}
    }
}