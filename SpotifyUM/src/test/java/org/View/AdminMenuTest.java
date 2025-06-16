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
 * Tests for AdminMenu
 */
public class AdminMenuTest {
    private Controller mockController;
    private MenuManager menuManager;
    private AdminMenu menu;

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setup() {
        System.setOut(new PrintStream(out));
        mockController = mock(Controller.class);
        menuManager = new MenuManager(new Controller(new SpotifUM()));
        menu = new AdminMenu(mockController, menuManager);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testImportDatabase() {
        when(mockController.importModel("file.db")).thenReturn("import ok");
        simulate("1\nfile.db\n");
        menu.handleInput();
        assertTrue(out.toString().contains("import ok"));
    }

    @Test
    void testExportDatabase() {
        when(mockController.exportModel("out.db")).thenReturn("export ok");
        simulate("2\nout.db\n");
        menu.handleInput();
        assertTrue(out.toString().contains("export ok"));
    }

    @Test
    void testNavigateStatistics() {
        simulate("4\n");
        menu.handleInput();
        assertTrue(menuManager.getCurrentMenu() instanceof StatisticsMenu);
    }

    @Test
    void testLogoutToLogin() {
        simulate("0\n");
        menu.handleInput();
        assertTrue(menuManager.getCurrentMenu() instanceof LoginMenu);
    }

    @Test
    void testInvalidOption() {
        simulate("9\n");
        menu.handleInput();
        assertTrue(out.toString().contains("Opção inválida"));
    }

    @Test
    void testNonNumeric() {
        simulate("x\n");
        menu.handleInput();
        assertTrue(out.toString().contains("insira apenas Números"));
    }

    private void simulate(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        menu.setScanner(new Scanner(System.in));
    }
}