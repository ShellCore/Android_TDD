package mx.dev.shellcore.android.groovy.playlist

import mx.dev.shellcore.android.groovy.playlist_detail.PlaylistDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaylistApi {

    @GET("playlists")
    suspend fun fetchAllPlaylists(): List<PlaylistRaw>

    @GET("playlist-details/{id}")
    suspend fun fetchPlaylistById(@Path("id") id: String): PlaylistDetail
}
