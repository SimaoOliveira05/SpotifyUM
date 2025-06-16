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
 * Tests for the LoginMenu class.
 */
public class LoginMenuTest {
    
    private Controller controller;
    private MenuManager menuManager;
    private LoginMenu loginMenu;
    
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
        
        // Create a fresh login menu instance for each test
        loginMenu = new LoginMenu(controller, menuManager);
    }
    
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    @Test
    void testHandleInputExit() {
        // Simulate user selecting option 3 (exit)
        simulateUserInput("3\n");
        
        // Call handleInput() to process the simulated input
        loginMenu.handleInput();
        
        // Check that isRunning is set to false
        assertFalse(loginMenu.isRunning());
    }
    
    @Test
    void testHandleInputAdmin() {
        // Simulate user selecting option 4 (admin menu)
        simulateUserInput("4\n");
        
        // Call handleInput() to process the simulated input
        loginMenu.handleInput();
        
        // Check that the menu was changed to AdminMenu
        assertTrue(menuManager.getCurrentMenu() instanceof AdminMenu);
    }
    
    @Test
    void testHandleInputInvalidOption() {
        // Simulate user selecting an invalid option
        simulateUserInput("99\n");
        
        // Call handleInput() to process the simulated input
        loginMenu.handleInput();
        
        // The output should contain an error message
        String output = outputStream.toString();
        assertTrue(output.contains("Opção inválida"));
    }
    
    @Test
    void testHandleInputNonNumeric() {
        // Simulate user entering non-numeric input
        simulateUserInput("abc\n");
        
        // Call handleInput() to process the simulated input
        loginMenu.handleInput();
        
        // The output should contain an error message
        String output = outputStream.toString();
        assertTrue(output.contains("insira apenas Números"));
    }
    
    @Test
    void testRegister() {
        // Simulate user registration input
        simulateUserInput("testUser\ntestEmail@example.com\ntestAddress\ntestPassword\n");
        
        // Call register() to process the simulated input
        loginMenu.register();
        
        // Try to login with the registered credentials
        simulateUserInput("testUser\ntestPassword\n");
        loginMenu.login();
        
        // The output should contain a success message
        String output = outputStream.toString();
        assertTrue(output.contains("testUser"));
    }
    
    /**
     * Helper method to simulate user input
     */
    private void simulateUserInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        loginMenu.setScanner(new Scanner(System.in));
    }
}