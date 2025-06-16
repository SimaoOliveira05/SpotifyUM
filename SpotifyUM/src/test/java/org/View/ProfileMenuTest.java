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
import static org.mockito.Mockito.*;

/**
 * Tests for the ProfileMenu class.
 */
public class ProfileMenuTest {
    
    private Controller mockController;
    private MenuManager menuManager;
    private ProfileMenu profileMenu;
    
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
        
        // Create a ProfileMenu with the mock controller
        profileMenu = new ProfileMenu(mockController, menuManager);
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
        profileMenu.handleInput();
        
        // Check that the menu was changed to MainMenu
        assertTrue(menuManager.getCurrentMenu() instanceof MainMenu);
    }
    
    @Test
    void testHandleInputEditProfile() {
        // Simulate user selecting option 2 (edit profile)
        simulateUserInput("2\n");
        
        // Call handleInput() to process the simulated input
        profileMenu.handleInput();
        
        // Check that the menu was changed to ProfileEditorMenu
        assertTrue(menuManager.getCurrentMenu() instanceof ProfileEditorMenu);
    }
    
    @Test
    void testHandleInputChangePlans() {

        
        simulateUserInput("4\n");
        
        // Call handleInput() to process the simulated input
        profileMenu.handleInput();
        
        // Check that the menu was changed to PlanMenu
        assertTrue(menuManager.getCurrentMenu() instanceof PlanMenu);
    }
    

    
    @Test
    void testHandleInputInvalidOption() {
        // Simulate user selecting an invalid option
        simulateUserInput("99\n");
        
        // Call handleInput() to process the simulated input
        profileMenu.handleInput();
        
        // The output should contain an error message
        String output = outputStream.toString();
        assertTrue(output.contains("Opção inválida"));
    }
    
    @Test
    void testHandleInputNonNumeric() {
        // Simulate user entering non-numeric input
        simulateUserInput("abc\n");
        
        // Call handleInput() to process the simulated input
        profileMenu.handleInput();
        
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
        profileMenu.setScanner(new Scanner(System.in));
    }
}