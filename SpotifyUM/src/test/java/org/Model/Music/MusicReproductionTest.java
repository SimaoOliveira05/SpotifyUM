package org.Model.Music;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MusicReproductionTest {
    
    private MusicReproduction musicReproduction;
    private Music music;
    private LocalDate testDate;
    
    @BeforeEach
    public void setUp() {
        // Create a test Music object
        music = new Music("Bohemian Rhapsody", "Queen", "EMI", 
                         "Is this the real life?", "Musical figures", 
                         "Rock", "A Night at the Opera", 355, false);
        
        // Create a specific date for testing (fixed date to ensure consistent tests)
        testDate = LocalDate.of(2023, 5, 15);
        
        // Create MusicReproduction with the test music and date
        musicReproduction = new MusicReproduction(music, testDate);
    }
    
    @Test
    public void testConstructorWithDate() {
        assertEquals(music, musicReproduction.getMusic());
        assertEquals(testDate, musicReproduction.getDate());
    }
    
    @Test
    public void testConstructorWithoutDate() {
        // Create a new MusicReproduction with just music
        MusicReproduction newReproduction = new MusicReproduction(music);
        
        // The music should be the same
        assertEquals(music, newReproduction.getMusic());
        
        // The date should be today
        assertEquals(LocalDate.now(), newReproduction.getDate());
    }
    
    @Test
    public void testCopyConstructor() {
        MusicReproduction copy = new MusicReproduction(musicReproduction);
        
        // Both music and date should be the same
        assertEquals(musicReproduction.getMusic(), copy.getMusic());
        assertEquals(musicReproduction.getDate(), copy.getDate());
    }
    
    @Test
    public void testSetters() {
        // Create a new music object
        Music newMusic = new Music("Another One Bites the Dust", "Queen", "EMI",
                                  "Another one bites the dust", "F-G-A",
                                  "Rock", "The Game", 215, false);
        LocalDate newDate = LocalDate.of(2023, 6, 20);
        
        // Set new values
        musicReproduction.setMusic(newMusic);
        musicReproduction.setDate(newDate);
        
        // Check if values were updated
        assertEquals(newMusic, musicReproduction.getMusic());
        assertEquals(newDate, musicReproduction.getDate());
    }
    
    @Test
    public void testClone() {
        MusicReproduction clone = musicReproduction.clone();
        
        // Should have the same values
        assertEquals(musicReproduction.getMusic(), clone.getMusic());
        assertEquals(musicReproduction.getDate(), clone.getDate());
        
        // But should be a different object
        assertNotSame(musicReproduction, clone);
    }
    
    @Test
    public void testToString() {
        String result = musicReproduction.toString();
        
        // The string representation should contain both music and date information
        assertTrue(result.contains("music"));
        assertTrue(result.contains("date"));
    }
}