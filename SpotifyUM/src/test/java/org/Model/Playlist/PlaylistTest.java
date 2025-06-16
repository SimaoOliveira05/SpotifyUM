package org.Model.Playlist;

import org.Exceptions.AlreadyExistsException;
import org.Model.Music.Music;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {
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

    private Playlist playlist;
    private Music music1;
    private Music music2;

    @BeforeEach
    void setUp() {
        music1 = new Music(NAME1, INTERPRETER1, PUBLISHER1, LYRICS1, MUSICAL_FIGURES1, GENRE1, ALBUM1, DURATION1, EXPLICIT1);
        music2 = new Music(NAME2, INTERPRETER2, PUBLISHER2, LYRICS2, MUSICAL_FIGURES2, GENRE2, ALBUM2, DURATION2, EXPLICIT2);
        List<Music> musics = new ArrayList<>();
        musics.add(music1);
        playlist = new Playlist("Queen Hits", "Fan", musics);
    }

    @Test
    void testConstructorWithMusics() {
        assertEquals("Queen Hits", playlist.getName());
        assertEquals("Fan", playlist.getAutor());
        assertEquals(1, playlist.getMusics().size());
        assertEquals(music1, playlist.getMusics().get(0));
    }

    @Test
    void testConstructorWithoutMusics() {
        Playlist emptyPlaylist = new Playlist("Empty Playlist", "Fan");
        assertEquals("Empty Playlist", emptyPlaylist.getName());
        assertEquals("Fan", emptyPlaylist.getAutor());
        assertTrue(emptyPlaylist.getMusics().isEmpty());
    }

    @Test
    void testAddMusic() throws AlreadyExistsException {
        playlist.addMusic(music2);
        assertEquals(2, playlist.getMusics().size());
        assertTrue(playlist.getMusics().contains(music2));
    }

    @Test
    void testAddMusicThrowsAlreadyExistsException() {
        assertThrows(AlreadyExistsException.class, () -> playlist.addMusic(music1));
    }

    @Test
    void testRemoveMusic() {
        assertTrue(playlist.removeMusic(music1));
        assertFalse(playlist.getMusics().contains(music1));
    }

    @Test
    void testRemoveMusicNotInPlaylist() {
        assertFalse(playlist.removeMusic(music2));
    }

    @Test
    void testGetMusicByIndex() {
        assertEquals(music1, playlist.getMusicBYIndex(0));
    }

    @Test
    void testGetMusicByIndexThrowsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.getMusicBYIndex(1));
    }

    @Test
    void testClone() {
        Playlist clonedPlaylist = playlist.clone();
        assertEquals(playlist, clonedPlaylist);
        assertNotSame(playlist, clonedPlaylist);
    }

    @Test
    void testEquals() {
        Playlist samePlaylist = new Playlist(playlist);
        assertEquals(playlist, samePlaylist);
    }

    @Test
    void testHashCode() {
        assertEquals(playlist.getId(), playlist.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Playlist{nome='Queen Hits', autor='Fan', musicas=[" + music1.toString() + "]}";
        assertEquals(expected, playlist.toString());
    }
}