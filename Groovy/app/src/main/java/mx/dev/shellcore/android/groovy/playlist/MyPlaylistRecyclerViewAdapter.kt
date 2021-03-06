package mx.dev.shellcore.android.groovy.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.dev.shellcore.android.groovy.R
import mx.dev.shellcore.android.groovy.databinding.PlaylistItemBinding

class MyPlaylistRecyclerViewAdapter(
    private val values: List<Playlist>,
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<MyPlaylistRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(values[position])

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(playlist: Playlist) {
            binding.apply {
                playlistName.text = playlist.name
                playlistCategory.text = playlist.category
                playlistImage.setImageResource(playlist.image)
                playlistItemRoot.setOnClickListener {
                    listener(playlist.id)
                }
            }
        }
    }
}