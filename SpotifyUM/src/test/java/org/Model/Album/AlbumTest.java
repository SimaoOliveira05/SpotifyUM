package org.Model.Album;

import org.Model.Music.Music;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlbumTest {
    private Music m1, m2;
    private List<Music> originalList;

    @BeforeEach
    void init() {
        m1 = new Music("Song1", "Artist1", "Pub", "Lyrics1", "Fig1", "Genre1", "Alb1", 120, false);
        m2 = new Music("Song2", "Artist2", "Pub", "Lyrics2", "Fig2", "Genre2", "Alb1", 200, true);
        originalList = new ArrayList<>();
        originalList.add(m1);
        originalList.add(m2);
    }

    @Test
    void testDefaultConstructor() {
        Album a = new Album();
        assertEquals("", a.getName());
        assertEquals("", a.getArtist());
        // musics is null on default, setting new list
        List<Music> list = List.of(m1);
        a.setMusics(list);
        List<Music> copy = a.getMusics();
        assertEquals(1, copy.size());
        assertNotSame(list, copy);
    }

    @Test
    void testNameArtistConstructor() {
        Album a = new Album("MyAlbum", "MyArtist");
        assertEquals("MyAlbum", a.getName());
        assertEquals("MyArtist", a.getArtist());
        List<Music> mus = a.getMusics();
        assertNotNull(mus);
        assertTrue(mus.isEmpty());
    }

    @Test
    void testFullConstructorDeepCopy() {
        Album a = new Album("A", "B", originalList);
        // original list mutated -> album unaffected
        originalList.clear();
        List<Music> musics = a.getMusics();
        assertEquals(2, musics.size());
        // elements are clones not same instances
        assertNotSame(m1, musics.get(0));
    }

    @Test
    void testCopyConstructor() {
        Album a1 = new Album("A", "B", originalList);
        Album a2 = new Album(a1);
        assertEquals(a1.getName(), a2.getName());
        assertEquals(a1.getArtist(), a2.getArtist());
        // deep copy of musics
        List<Music> l1 = a1.getMusics();
        List<Music> l2 = a2.getMusics();
        assertEquals(l1.size(), l2.size());
        assertNotSame(l1, l2);
        assertNotSame(l1.get(0), l2.get(0));
    }

    @Test
    void testCloneMethod() {
        Album a = new Album("A", "B", originalList);
        Album c = a.clone();
        assertEquals(a.getName(), c.getName());
        assertEquals(a.getArtist(), c.getArtist());
        // deep copy
        List<Music> l1 = a.getMusics();
        List<Music> l2 = c.getMusics();
        assertNotSame(l1, l2);
    }

    @Test
    void testAddRemoveGetMusic() {
        Album a = new Album("X", "Y");
        a.addMusic(m1);
        assertEquals(1, a.getMusics().size());
        // add and remove
        a.addMusic(m2);
        assertEquals(2, a.getMusics().size());
        a.removeMusic(m1);
        assertEquals(1, a.getMusics().size());
        // getMusicByName
        Music found = a.getMusicByName("Song2");
        assertNotNull(found);
        assertEquals("Song2", found.getName());
        assertNull(a.getMusicByName("NoSong"));
    }

    @Test
    void testToString() {
        Album a = new Album("A", "B", originalList);
        String s = a.toString();
        assertTrue(s.contains("Album: A"));
        assertTrue(s.contains("Artista: B"));
        assertTrue(s.contains("Song1"));
        assertTrue(s.contains("Song2"));
    }
}