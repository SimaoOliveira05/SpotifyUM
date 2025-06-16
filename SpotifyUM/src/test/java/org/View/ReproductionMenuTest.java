package org.View;

import org.Controller.Controller;
import org.Controller.dtos.MusicInfo;
import org.Model.SpotifUM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the ReproductionMenu class.
 */
public class ReproductionMenuTest {
    
    // We'll need to mock the Controller to avoid actual music playback
    private Controller mockController;
    private MenuManager menuManager;
    private ReproductionMenu reproductionMenu;
    
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    
    @BeforeEach
    void setUp() {
        // Set up output capture
        System.setOut(new PrintStream(outputStream));
        
        // Create a mock controller
        mockController = mock(Controller.class);
        // Create a real menu manager
        menuManager = new MenuManager(new Controller(new SpotifUM()));
        
        // Configure the mock controller with basic behavior
        when(mockController.canCurrentUserChooseWhatToPlay()).thenReturn(true);
        when(mockController.currentUserAccessToFavorites()).thenReturn(true);
        when(mockController.canCurrentUserSkip()).thenReturn(true);
        
        // Create a ReproductionMenu with the mock controller
        reproductionMenu = new ReproductionMenu(mockController, menuManager);
    }
    
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    @Test
    void testHandleInputBackToMainMenu() {
        // Simulate user selecting option 0 (back to main menu)
        simulateUserInput("0\n");
        
        // Call handleInput() to process the simulated input
        reproductionMenu.handleInput();
        
        // Check that the menu was changed to MainMenu
        assertTrue(menuManager.getCurrentMenu() instanceof MainMenu);
    }
    
    @Test
    void testHandleInputInvalidOption() {
        // Simulate user selecting an invalid option
        simulateUserInput("99\n");
        
        // Call handleInput() to process the simulated input
        reproductionMenu.handleInput();
        
        // The output should contain an error message
        String output = outputStream.toString();
        assertTrue(output.contains("Opção inválida"));
    }
    
    @Test
    void testHandleInputNonNumeric() {
        // Simulate user entering non-numeric input
        simulateUserInput("abc\n");
        
        // Call handleInput() to process the simulated input
        reproductionMenu.handleInput();
        
        // The output should contain an error message
        String output = outputStream.toString();
        assertTrue(output.contains("insira apenas números"));
    }
    
    @Test
    void testRandomPlaylistWithNoPlaylists() {
        // Mock the controller to return an empty playlist
        when(mockController.getRandomPlaylistMusicNames()).thenReturn(Optional.empty());
        
        // Call the method
        reproductionMenu.playRandomPlaylist();
        
        // Verify the error message
        String output = outputStream.toString();
        assertTrue(output.contains("Não existem playlists aleatórias disponíveis"));
    }
    
    @Test
    void testRandomPlaylistWithValidPlaylist() {
        // Create a list of music names for testing
        List<String> musicNames = Arrays.asList("Song1", "Song2", "Song3");
        
        // Mock the controller to return the test playlist and a basic MusicInfo
        when(mockController.getRandomPlaylistMusicNames()).thenReturn(Optional.of(musicNames));
        
        // Create a MusicInfo object with the proper constructor
        // Using the constructor that takes: musicName, artistName, lyrics, url, isExplicit
        MusicInfo mockMusicInfo = new MusicInfo("Test Song", "Test Artist", "Test lyrics here", "", false);
        when(mockController.playMusic(anyString())).thenReturn(mockMusicInfo);
        
        // Simulate user input for confirmation and quitting playback
        simulateUserInput("n\nq\n");
        
        // Call the method
        reproductionMenu.playRandomPlaylist();
        
        // Verify that playMusic was called with the first song
        verify(mockController).playMusic("Song1");
        
        // Verify output contains playback information
        String output = outputStream.toString();
        assertTrue(output.contains("Agora a tocar:") || output.contains("Letra de:"));
    }
    
    @Test
    void testFavoritePlaylistWithNoMusics() {
        // Mock the controller to return an empty playlist
        when(mockController.getFavoritePlaylistMusicNames(anyBoolean(), anyInt())).thenReturn(Optional.empty());
        
        // Simulate user input for the options
        simulateUserInput("n\nn\n");
        
        // Call the method
        reproductionMenu.playFavoritePlaylist();
        
        // Verify the error message
        String output = outputStream.toString();
        assertTrue(output.contains("Não existem músicas disponíveis para a playlist"));
    }
    
    /**
     * Helper method to simulate user input
     */
    private void simulateUserInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        reproductionMenu.setScanner(new Scanner(System.in));
    }
}