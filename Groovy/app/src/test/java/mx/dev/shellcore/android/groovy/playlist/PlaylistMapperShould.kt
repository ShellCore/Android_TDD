package mx.dev.shellcore.android.groovy.playlist

import mx.dev.shellcore.android.groovy.R
import mx.dev.shellcore.android.groovy.utils.BaseUnitTest
import org.junit.Assert.*
import org.junit.Test

class PlaylistMapperShould: BaseUnitTest() {

    private val mapper = PlaylistMapper()

    private val playlistRaw = PlaylistRaw("1", "Name", "Category")
    private val playlists = mapper.invoke(listOf(playlistRaw))
    private val playlist = playlists[0]

    private val playlistRawRock = PlaylistRaw("1", "Name", "rock")
    private val playlistsRock = mapper.invoke(listOf(playlistRawRock))
    private val playlistRock = playlistsRock[0]

    @Test
    fun keepSameId() {
        assertEquals(playlistRaw.id, playlist.id)
    }

    @Test
    fun keepSameName() {
        assertEquals(playlistRaw.name, playlist.name)
    }

    @Test
    fun keepSameCategory() {
        assertEquals(playlistRaw.category, playlist.category)
    }

    @Test
    fun mapDefaultImageWhenNotRock() {
        assertEquals(R.mipmap.playlist, playlist.image)
    }

    @Test
    fun mapRockImageWhenRockCategory() {
        assertEquals(R.mipmap.rock, playlistRock.image)
    }
}