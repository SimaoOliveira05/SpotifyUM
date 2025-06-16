package org.View;

import org.Controller.Controller;
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
 * Tests for ProfileEditorMenu
 */
public class ProfileEditorMenuTest {
    private Controller mockController;
    private MenuManager menuManager;
    private ProfileEditorMenu menu;

    private ByteArrayOutputStream out;
    private PrintStream originalOut;
    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        originalOut = System.out;
        originalIn = System.in;
        System.setOut(new PrintStream(out));

        mockController = mock(Controller.class);
        menuManager = new MenuManager(mockController);
        menu = new ProfileEditorMenu(mockController, menuManager);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testChangeName() {
        when(mockController.changeCurrentUserUserName("newName")).thenReturn("name ok");
        simulateInput("1\nnewName\n");
        menu.handleInput();
        assertTrue(out.toString().contains("name ok"));
    }

    @Test
    void testChangeEmail() {
        when(mockController.changeCurrentUserEmail("new@example.com")).thenReturn("email ok");
        simulateInput("2\nnew@example.com\n");
        menu.handleInput();
        assertTrue(out.toString().contains("email ok"));
    }

    @Test
    void testChangePasswordIncorrect() {
        when(mockController.isPasswordCorrect("wrongPass")).thenReturn(false);
        simulateInput("3\nwrongPass\n");
        menu.handleInput();
        assertTrue(out.toString().contains("Password atual incorreta"));
    }

    @Test
    void testChangePasswordCorrect() {
        when(mockController.isPasswordCorrect("oldPass")).thenReturn(true);
        when(mockController.changeCurrentUserPassword("newPass")).thenReturn("pass ok");
        simulateInput("3\noldPass\nnewPass\n");
        menu.handleInput();
        assertTrue(out.toString().contains("pass ok"));
    }

    @Test
    void testNavigatePlans() {
        simulateInput("4\n");
        menu.handleInput();
        assertTrue(menuManager.getCurrentMenu() instanceof PlanMenu);
    }

    @Test
    void testBackToProfileMenu() {
        simulateInput("0\n");
        menu.handleInput();
        assertTrue(menuManager.getCurrentMenu() instanceof ProfileMenu);
    }

    @Test
    void testInvalidOption() {
        simulateInput("9\n");
        menu.handleInput();
        assertTrue(out.toString().contains("Opção inválida"));
    }

    @Test
    void testNonNumericInput() {
        simulateInput("x\n");
        menu.handleInput();
        assertTrue(out.toString().toLowerCase().contains("insira apenas números"));
    }

    private void simulateInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        menu.setScanner(new Scanner(System.in));
    }
}