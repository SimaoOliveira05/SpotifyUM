package org.Model.Playlist;

import org.Model.Music.Music;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PlaylistRandomTest {

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
        music1 = new Music(NAME1, INTERPRETER1, PUBLISHER1, LYRICS1, MUSICAL_FIGURES1, GENRE1, ALBUM1, DURATION1, EXPLICIT1);
        music2 = new Music(NAME2, INTERPRETER2, PUBLISHER2, LYRICS2, MUSICAL_FIGURES2, GENRE2, ALBUM2, DURATION2, EXPLICIT2);
    }

    @Test
    void testConstructorWithNameAndMusic() {
        List<Music> musicList = new ArrayList<>();
        musicList.add(music1);
        musicList.add(music2);

        PlaylistRandom playlist = new PlaylistRandom("Random Playlist", musicList);

        assertEquals("Random Playlist", playlist.getName());
        assertEquals("SpotifyUM", playlist.getAutor());
        assertEquals(musicList, playlist.getMusics());
    }

    @Test
    void testDefaultConstructor() {
        PlaylistRandom playlist = new PlaylistRandom();

        assertEquals("", playlist.getName());
        assertEquals("", playlist.getAutor());
        assertTrue(playlist.getMusics().isEmpty());
    }

    @Test
    void testCopyConstructor() {
        List<Music> musicList = new ArrayList<>();
        musicList.add(music1);
        Playlist original = new Playlist("Original Playlist", "Author", musicList);

        PlaylistRandom copiedPlaylist = new PlaylistRandom(original);

        assertEquals(original.getName(), copiedPlaylist.getName());
        assertEquals(original.getAutor(), copiedPlaylist.getAutor());
        assertEquals(original.getMusics(), copiedPlaylist.getMusics());
    }

    @Test
    void testToString() {
        List<Music> musicList = new ArrayList<>();
        musicList.add(music1);
        PlaylistRandom playlist = new PlaylistRandom("Random Playlist", musicList);

        String expected = "Playlist Aleatória{nome='Random Playlist', autor='SpotifyUM', musicas=" + musicList + "}";
        assertEquals(expected, playlist.toString());
    }

    @Test
    void testClone() {
        List<Music> musicList = new ArrayList<>();
        musicList.add(music1);
        PlaylistRandom playlist = new PlaylistRandom("Random Playlist", musicList);

        PlaylistRandom clonedPlaylist = playlist.clone();

        assertEquals(playlist, clonedPlaylist);
        assertNotSame(playlist, clonedPlaylist);
    }

    @Test
    void testEquals() {
        List<Music> musicList1 = new ArrayList<>();
        musicList1.add(music1);
        PlaylistRandom playlist1 = new PlaylistRandom("Random Playlist", musicList1);
        List<Music> musicList2 = new ArrayList<>();
        musicList2.add(music1);
        PlaylistRandom playlist2 = new PlaylistRandom("Random Playlist", musicList2);
        List<Music> musicList3 = new ArrayList<>();
        musicList3.add(music2);
        PlaylistRandom playlist3 = new PlaylistRandom("Another Playlist", musicList3);

        assertEquals(playlist1, playlist2);
        assertNotEquals(playlist1, playlist3);
        assertNotEquals(playlist2, playlist3);
    }
}