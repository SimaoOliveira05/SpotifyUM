package org.View;

import org.Controller.Controller;
import org.Model.SpotifUM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the MenuManager class.
 */
public class MenuManagerTest {
    
    private Controller mockController;
    private MenuManager menuManager;

    @BeforeEach
    void setUp() {
        // Create a mock controller with a real SpotifUM instance
        SpotifUM spotifUM = new SpotifUM();
        mockController = new Controller(spotifUM);
        menuManager = new MenuManager(mockController);
    }

    @Test
    void testInitialMenuIsLoginMenu() {
        // When a MenuManager is created, it should start with LoginMenu
        assertTrue(menuManager.getCurrentMenu() instanceof LoginMenu);
    }

    @Test
    void testSetMenu() {
        // Create a mock menu (we'll use MainMenu for this test)
        Menu newMenu = new MainMenu(mockController, menuManager);
        
        // Set the new menu
        menuManager.setMenu(newMenu);
        
        // Verify the current menu was changed
        assertEquals(newMenu, menuManager.getCurrentMenu());
    }
}