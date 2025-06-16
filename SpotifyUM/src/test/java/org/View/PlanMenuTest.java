package org.View;

import org.Controller.Controller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class PlanMenuTest {
    private Controller mockController;
    private MenuManager menuManager;
    private PlanMenu menu;
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
        menu = new PlanMenu(mockController, menuManager);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testSetFreePlan() {
        when(mockController.setFreePlan()).thenReturn("free ok");
        simulate("1\n");
        menu.handleInput();
        assertTrue(out.toString().contains("free ok"));
    }

    @Test
    void testSetPremiumBasePlan() {
        when(mockController.setPremiumBasePlan()).thenReturn("base ok");
        simulate("2\n");
        menu.handleInput();
        assertTrue(out.toString().contains("base ok"));
    }

    @Test
    void testSetPremiumTopPlan() {
        when(mockController.setPremiumTopPlan()).thenReturn("top ok");
        simulate("3\n");
        menu.handleInput();
        assertTrue(out.toString().contains("top ok"));
    }

    @Test
    void testBackOption() {
        simulate("0\n");
        menu.handleInput();
        assertTrue(menuManager.getCurrentMenu() instanceof ProfileMenu);
    }

    @Test
    void testInvalidOption() {
        simulate("9\n");
        menu.handleInput();
        assertTrue(out.toString().contains("Opção inválida"));
    }

    @Test
    void testNonNumericInput() {
        simulate("abc\n");
        menu.handleInput();
        assertTrue(out.toString().toLowerCase().contains("insira apenas números"));
    }

    private void simulate(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        menu.setScanner(new Scanner(System.in));
    }
}