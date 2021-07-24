package mx.dev.shellcore.android.groovy.playlist_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import mx.dev.shellcore.android.groovy.R
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

        observePlaylistDetail(binding)
        observeLoader(binding)

        return binding.root
    }

    private fun observeLoader(binding: FragmentPlaylistDetailBinding) {
        viewModel.loader.observe(this as LifecycleOwner, { loading ->
            when (loading) {
                true -> binding.playlistDetailLoader.visibility = View.VISIBLE
                else -> binding.playlistDetailLoader.visibility = View.GONE
            }
        })
    }

    private fun observePlaylistDetail(binding: FragmentPlaylistDetailBinding) {
        viewModel.playlistDetail.observe(this as LifecycleOwner) { playlistDetail ->
            if (playlistDetail.getOrNull() != null) {
                setupContent(binding, playlistDetail.getOrNull()!!)
            } else {
                Snackbar.make(binding.root, R.string.generic_error, Snackbar.LENGTH_LONG)
                    .show()
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