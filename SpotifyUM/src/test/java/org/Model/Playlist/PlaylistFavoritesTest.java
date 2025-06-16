package org.Model.Playlist;

import org.Model.Music.Music;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PlaylistFavoritesTest {

    private final String NAME1 = "Bohemian Rhapsody";
    private final String INTERPRETER1 = "Queen";
    private final String PUBLISHER1 = "EMI";
    private final String LYRICS1 = "Is this the real life? Is this just fantasy?";
    private final String MUSICAL_FIGURES1 = "A-B-C-D";
    private final String GENRE1 = "Rock";
    private final String ALBUM1 = "A Night at the Opera";
    private final int DURATION1 = 355;
    private final boolean EXPLICIT1 = false;

    private final String NAME2 = "Another One Bites the Dust";
    private final String INTERPRETER2 = "Queen";
    private final String PUBLISHER2 = "EMI";
    private final String LYRICS2 = "Are you ready, hey, are you ready for this?";
    private final String MUSICAL_FIGURES2 = "E-F-G-A";
    private final String GENRE2 = "Rock";
    private final String ALBUM2 = "The Game";
    private final int DURATION2 = 215;
    private final boolean EXPLICIT2 = false;

    private Music music1;
    private Music music2;

    @BeforeEach
    void setUp() {
        music1 = new Music(NAME1, INTERPRETER1, PUBLISHER1, LYRICS1, MUSICAL_FIGURES1, GENRE1, ALBUM1, DURATION1,
                EXPLICIT1);
        music2 = new Music(NAME2, INTERPRETER2, PUBLISHER2, LYRICS2, MUSICAL_FIGURES2, GENRE2, ALBUM2, DURATION2,
                EXPLICIT2);
    }

    @Test
    void testConstructorWithMusicList() {
        List<Music> musicList = new ArrayList<>();
        musicList.add(music1);
        musicList.add(music2);

        PlaylistFavorites playlist = new PlaylistFavorites(musicList);

        assertEquals("Feito para Você", playlist.getName());
        assertEquals("SpotifyUM", playlist.getAutor());
        assertEquals(musicList, playlist.getMusics());
    }

    @Test
    void testEmptyConstructor() {
        PlaylistFavorites playlist = new PlaylistFavorites();

        assertEquals("", playlist.getName());
        assertEquals("", playlist.getAutor());
        assertTrue(playlist.getMusics().isEmpty());
    }

    @Test
    void testCopyConstructor() {
        List<Music> musicList = new ArrayList<>();
        musicList.add(music1);

        Playlist original = new Playlist("Original Playlist", "Author", musicList);
        PlaylistFavorites playlist = new PlaylistFavorites(original);

        assertEquals(original.getName(), playlist.getName());
        assertEquals(original.getAutor(), playlist.getAutor());
        assertEquals(original.getMusics(), playlist.getMusics());
    }

    @Test
    void testToString() {
        List<Music> musicList = new ArrayList<>();
        musicList.add(music1);

        PlaylistFavorites playlist = new PlaylistFavorites(musicList);

        assertTrue(playlist.toString().contains("Playlist Favoritos"));
        assertTrue(playlist.toString().contains("Feito para Você"));
        assertTrue(playlist.toString().contains("SpotifyUM"));
    }

    @Test
    void testClone() {
        List<Music> musicList = new ArrayList<>();
        musicList.add(music1);

        PlaylistFavorites playlist = new PlaylistFavorites(musicList);
        PlaylistRandom clonedPlaylist = playlist.clone();

        assertNotSame(playlist, clonedPlaylist);
        assertEquals(playlist.getName(), clonedPlaylist.getName());
        assertEquals(playlist.getAutor(), clonedPlaylist.getAutor());
        assertEquals(playlist.getMusics(), clonedPlaylist.getMusics());
    }

    @Test
    void testEquals() {
        List<Music> musicList = new ArrayList<>();
        musicList.add(music1);

        PlaylistFavorites playlist1 = new PlaylistFavorites(musicList);
        PlaylistFavorites playlist2 = new PlaylistFavorites(musicList);

        assertTrue(playlist1.equals(playlist2));
        assertFalse(playlist1.equals(null));
        assertFalse(playlist1.equals(new Object()));
    }
}