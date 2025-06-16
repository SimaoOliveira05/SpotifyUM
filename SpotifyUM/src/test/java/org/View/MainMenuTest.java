package org.View;

import org.Controller.Controller;
import org.Model.SpotifUM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the MainMenu class.
 */
public class MainMenuTest {
    
    private Controller controller;
    private MenuManager menuManager;
    private MainMenu mainMenu;
    
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    
    @BeforeEach
    void setUp() {
        // Set up output capture
        System.setOut(new PrintStream(outputStream));
        
        // Create necessary objects
        SpotifUM spotifUM = new SpotifUM();
        controller = new Controller(spotifUM);
        menuManager = new MenuManager(controller);
        
        // Create a main menu instance for each test
        mainMenu = new MainMenu(controller, menuManager);
    }
    
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    @Test
    void testHandleInputReproductionMenu() {
        // Simulate user selecting option 1 (Reproduction Menu)
        simulateUserInput("1\n");
        
        // Call handleInput() to process the simulated input
        mainMenu.handleInput();
        
        // Check that the menu was changed to ReproductionMenu
        assertTrue(menuManager.getCurrentMenu() instanceof ReproductionMenu);
    }
    
    @Test
    void testHandleInputProfileMenu() {
        // Simulate user selecting option 2 (Profile Menu)
        simulateUserInput("2\n");
        
        // Call handleInput() to process the simulated input
        mainMenu.handleInput();
        
        // Check that the menu was changed to ProfileMenu
        assertTrue(menuManager.getCurrentMenu() instanceof ProfileMenu);
    }
    
    @Test
    void testHandleInputLogout() {
        // Simulate user selecting option 0 (Logout)
        simulateUserInput("0\n");
        
        // Call handleInput() to process the simulated input
        mainMenu.handleInput();
        
        // Check that the menu was changed to LoginMenu
        assertTrue(menuManager.getCurrentMenu() instanceof LoginMenu);
    }
    
    @Test
    void testHandleInputInvalidOption() {
        // Simulate user selecting an invalid option
        simulateUserInput("99\n");
        
        // Call handleInput() to process the simulated input
        mainMenu.handleInput();
        
        // The output should contain an error message
        String output = outputStream.toString();
        assertTrue(output.contains("Opção inválida"));
    }
    
    @Test
    void testHandleInputNonNumeric() {
        // Simulate user entering non-numeric input
        simulateUserInput("abc\n");
        
        // Call handleInput() to process the simulated input
        mainMenu.handleInput();
        
        // The output should contain an error message
        String output = outputStream.toString();
        assertTrue(output.contains("insira apenas Números"));
    }
    
    /**
     * Helper method to simulate user input
     */
    private void simulateUserInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        mainMenu.setScanner(new Scanner(System.in));
    }
}