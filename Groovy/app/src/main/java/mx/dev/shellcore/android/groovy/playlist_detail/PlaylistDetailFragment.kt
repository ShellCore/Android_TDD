package mx.dev.shellcore.android.groovy.playlist_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import mx.dev.shellcore.android.groovy.PlaylistDetailFragmentArgs
import mx.dev.shellcore.android.groovy.databinding.FragmentPlaylistDetailBinding
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistDetailFragment : Fragment() {

    private val args: PlaylistDetailFragmentArgs by navArgs()

    lateinit var viewModel: PlaylistDetailViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistDetailViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPlaylistDetailBinding.inflate(inflater, container, false)

        val id = args.playlistId

        setupViewModel()

        viewModel.getPlaylistDetail(id)

        observeLiveData(binding)

        return binding.root
    }

    private fun observeLiveData(binding: FragmentPlaylistDetailBinding) {
        viewModel.playlistDetail.observe(this as LifecycleOwner) { playlistDetail ->
            if (playlistDetail.getOrNull() != null) {
                setupContent(binding, playlistDetail.getOrNull()!!)
            } else {
                // TODO
            }
        }
    }

    private fun setupContent(binding: FragmentPlaylistDetailBinding, playlistDetail: PlaylistDetail) {
        binding.playlistTitle.text = playlistDetail.name
        binding.playlistContent.text = playlistDetail.details
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PlaylistDetailViewModel::class.java)
    }
}