package mx.dev.shellcore.android.groovy.playlist

import mx.dev.shellcore.android.groovy.R

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int = R.mipmap.playlist
)