package org.Model.Music;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MusicMultimediaTest {
    
    private MusicMultimedia musicMultimedia;
    private final String NAME = "Bohemian Rhapsody";
    private final String INTERPRETER = "Queen";
    private final String PUBLISHER = "EMI";
    private final String LYRICS = "Is this the real life? Is this just fantasy?";
    private final String MUSICAL_FIGURES = "A-B-C-D";
    private final String GENRE = "Rock";
    private final String ALBUM = "A Night at the Opera";
    private final int DURATION = 355;
    private final boolean EXPLICIT = false;
    private final String URL = "https://www.youtube.com/watch?v=fJ9rUzIMcZQ";
    
    @BeforeEach
    public void setUp() {
        // Make sure the musicMultimedia object is properly initialized
        musicMultimedia = new MusicMultimedia(NAME, INTERPRETER, PUBLISHER, LYRICS, MUSICAL_FIGURES, 
                          GENRE, ALBUM, DURATION, EXPLICIT, URL);
    }
    
    @Test
    public void testDefaultConstructor() {
        MusicMultimedia defaultMusic = new MusicMultimedia();
        
        assertEquals("", defaultMusic.getName());
        assertEquals("", defaultMusic.getInterpreter());
        assertEquals("", defaultMusic.getPublisher());
        assertEquals("", defaultMusic.getLyrics());
        assertEquals("", defaultMusic.getMusicalFigures());
        assertEquals("", defaultMusic.getGenre());
        assertEquals("", defaultMusic.getAlbum());
        assertEquals(0, defaultMusic.getDuration());
        assertEquals(0, defaultMusic.getReproductions());
        assertFalse(defaultMusic.isExplicit());
        assertEquals("", defaultMusic.getUrl());
    }
    
    @Test
    public void testConstructor() {
        assertEquals(NAME, musicMultimedia.getName());
        assertEquals(INTERPRETER, musicMultimedia.getInterpreter());
        assertEquals(PUBLISHER, musicMultimedia.getPublisher());
        assertEquals(LYRICS, musicMultimedia.getLyrics());
        assertEquals(MUSICAL_FIGURES, musicMultimedia.getMusicalFigures());
        assertEquals(GENRE, musicMultimedia.getGenre());
        assertEquals(ALBUM, musicMultimedia.getAlbum());
        assertEquals(DURATION, musicMultimedia.getDuration());
        assertEquals(0, musicMultimedia.getReproductions());
        assertEquals(EXPLICIT, musicMultimedia.isExplicit());
        assertEquals(URL, musicMultimedia.getUrl());
    }
    
    @Test
    public void testCopyConstructor() {
        MusicMultimedia musicCopy = new MusicMultimedia(musicMultimedia);
        
        assertEquals(musicMultimedia.getName(), musicCopy.getName());
        assertEquals(musicMultimedia.getInterpreter(), musicCopy.getInterpreter());
        assertEquals(musicMultimedia.getPublisher(), musicCopy.getPublisher());
        assertEquals(musicMultimedia.getLyrics(), musicCopy.getLyrics());
        assertEquals(musicMultimedia.getMusicalFigures(), musicCopy.getMusicalFigures());
        assertEquals(musicMultimedia.getGenre(), musicCopy.getGenre());
        assertEquals(musicMultimedia.getAlbum(), musicCopy.getAlbum());
        assertEquals(musicMultimedia.getDuration(), musicCopy.getDuration());
        assertEquals(musicMultimedia.getReproductions(), musicCopy.getReproductions());
        assertEquals(musicMultimedia.isExplicit(), musicCopy.isExplicit());
        assertEquals(musicMultimedia.getUrl(), musicCopy.getUrl());
    }
    
    @Test
    public void testSetUrl() {
        String newUrl = "https://www.youtube.com/watch?v=newVideo";
        musicMultimedia.setUrl(newUrl);
        assertEquals(newUrl, musicMultimedia.getUrl());
    }
    
    @Test
    public void testClone() {
        MusicMultimedia musicClone = musicMultimedia.clone();
        
        assertEquals(musicMultimedia.getName(), musicClone.getName());
        assertEquals(musicMultimedia.getInterpreter(), musicClone.getInterpreter());
        assertEquals(musicMultimedia.getPublisher(), musicClone.getPublisher());
        assertEquals(musicMultimedia.getLyrics(), musicClone.getLyrics());
        assertEquals(musicMultimedia.getMusicalFigures(), musicClone.getMusicalFigures());
        assertEquals(musicMultimedia.getGenre(), musicClone.getGenre());
        assertEquals(musicMultimedia.getAlbum(), musicClone.getAlbum());
        assertEquals(musicMultimedia.getDuration(), musicClone.getDuration());
        assertEquals(musicMultimedia.getReproductions(), musicClone.getReproductions());
        assertEquals(musicMultimedia.isExplicit(), musicClone.isExplicit());
        assertEquals(musicMultimedia.getUrl(), musicClone.getUrl());
        
        // Ensure it's a deep copy
        musicClone.setUrl("Different");
        assertNotEquals(musicMultimedia.getUrl(), musicClone.getUrl());
    }
    
    @Test
    public void testEquals() {
        // In Music class, equals() only checks if names are equal
        MusicMultimedia sameName = new MusicMultimedia(NAME, INTERPRETER, PUBLISHER, LYRICS, 
                                    MUSICAL_FIGURES, GENRE, ALBUM, DURATION, EXPLICIT, URL);
        MusicMultimedia differentName = new MusicMultimedia("Different", INTERPRETER, PUBLISHER, LYRICS, 
                                    MUSICAL_FIGURES, GENRE, ALBUM, DURATION, EXPLICIT, URL);
        
        assertEquals(musicMultimedia, musicMultimedia);
        assertEquals(musicMultimedia, sameName);         
        assertNotEquals(musicMultimedia, differentName);
        assertNotEquals(musicMultimedia, null);
        assertNotEquals(musicMultimedia, new Object());
    }
    
    @Test
    public void testToString() {
        String result = musicMultimedia.toString();
        
        assertTrue(result.contains(NAME));
        assertTrue(result.contains(INTERPRETER));
        assertTrue(result.contains(URL));
    }
    
    @Test
    public void testInheritance() {
        // Test that MusicMultimedia is a subtype of Music
        assertTrue(musicMultimedia instanceof Music);
        
        // Test that play method works as expected
        int initialReproductions = musicMultimedia.getReproductions();
        String result = musicMultimedia.play();
        
        assertEquals(LYRICS, result);
        assertEquals(initialReproductions + 1, musicMultimedia.getReproductions());
    }
}