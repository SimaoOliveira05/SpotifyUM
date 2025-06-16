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
import java.time.LocalDate;
import java.util.Scanner;

public class StatisticsMenuTest {
    private Controller mockController;
    private MenuManager menuManager;
    private StatisticsMenu menu;
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
        menu = new StatisticsMenu(mockController, menuManager);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testOption1_MostReproducedMusic() {
        when(mockController.getMostReproducedMusic()).thenReturn("music ok");
        simulate("1\n");
        menu.handleInput();
        assertTrue(out.toString().contains("music ok"));
    }

    @Test
    void testOption2_MostReproducedArtist() {
        when(mockController.getMostReproducedArtist()).thenReturn("artist ok");
        simulate("2\n");
        menu.handleInput();
        assertTrue(out.toString().contains("artist ok"));
    }

    @Test
    void testOption3_NoInterval() {
        when(mockController.getUserWithMostReproductions()).thenReturn("user ok");
        simulate("3\nn\n");
        menu.handleInput();
        assertTrue(out.toString().contains("user ok"));
    }

    @Test
    void testOption3_WithInterval() {
        LocalDate start = LocalDate.of(2020,1,1);
        LocalDate end = LocalDate.of(2020,2,2);
        when(mockController.getUserWithMostReproductions(start, end)).thenReturn("interval ok");
        simulate("3\ns\n01-01-2020\n02-02-2020\n");
        menu.handleInput();
        assertTrue(out.toString().contains("interval ok"));
    }

    @Test
    void testOption4_UserWithMostPoints() {
        when(mockController.getUserWithMostPoints()).thenReturn("points ok");
        simulate("4\n");
        menu.handleInput();
        assertTrue(out.toString().contains("points ok"));
    }

    @Test
    void testOption5_MostReproducedGenre() {
        when(mockController.getMostReproducedGenre()).thenReturn("genre ok");
        simulate("5\n");
        menu.handleInput();
        assertTrue(out.toString().contains("genre ok"));
    }

    @Test
    void testOption6_PublicPlaylistCount() {
        when(mockController.getPublicPlaylistCount()).thenReturn("count ok");
        simulate("6\n");
        menu.handleInput();
        assertTrue(out.toString().contains("count ok"));
    }

    @Test
    void testOption7_UserWithMostPlaylists() {
        when(mockController.getUserWithMostPlaylists()).thenReturn("playlists ok");
        simulate("7\n");
        menu.handleInput();
        assertTrue(out.toString().contains("playlists ok"));
    }

    @Test
    void testOption0_BackToAdmin() {
        simulate("0\n");
        menu.handleInput();
        assertTrue(menuManager.getCurrentMenu() instanceof AdminMenu);
    }

    @Test
    void testInvalidOption() {
        simulate("99\n");
        menu.handleInput();
        assertTrue(out.toString().contains("Opção inválida"));
    }

    @Test
    void testNonNumericInput() {
        simulate("x\n");
        menu.handleInput();
        assertTrue(out.toString().toLowerCase().contains("entrada inválida"));
    }

    private void simulate(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        menu.setScanner(new Scanner(System.in));
    }
}
