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

public class PlaylistCreationMenuTest {
    private Controller mockController;
    private MenuManager menuManager;
    private PlaylistCreationMenu menu;

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(out));
        mockController = mock(Controller.class);
        menuManager = new MenuManager(new Controller(new SpotifUM()));
        menu = new PlaylistCreationMenu(mockController, menuManager);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testCreatePlaylist() {
        when(mockController.addToCurrentUserPlaylists("MyList")).thenReturn("added ok");
        simulate("1\nMyList\n");
        menu.handleInput();
        assertTrue(out.toString().contains("added ok"));
    }

    @Test
    void testCreatePlaylistWithGenreAndDuration() {
        when(mockController.listAllGenres()).thenReturn("G1,G2");
        when(mockController.createGenrePlaylist("GenList","G1",300)).thenReturn("genre ok");
        simulate("2\nGenList\nG1\n5\n");
        menu.handleInput();
        assertTrue(out.toString().contains("genre ok"));
    }

    @Test
    void testBackOption() {
        simulate("0\n");
        menu.handleInput();
        assertTrue(menuManager.getCurrentMenu() instanceof PlaylistMenu);
    }

    @Test
    void testInvalidOption() {
        simulate("9\n");
        menu.handleInput();
        assertTrue(out.toString().contains("Opção inválida"));
    }

    @Test
    void testExceptionHandling() {
        // non-numeric choice
        simulate("x\n");
        menu.handleInput();
        assertTrue(out.toString().contains("Erro"));
    }

    private void simulate(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        menu.setScanner(new Scanner(System.in));
    }
}