package mx.dev.shellcore.android.groovy.playlist

import retrofit2.http.GET

interface PlaylistApi {

    @GET("playlists")
    suspend fun fetchAllPlaylists(): List<Playlist>
}
