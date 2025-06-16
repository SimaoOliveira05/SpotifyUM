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

public class PlaylistMenuTest {
    private Controller mockController;
    private MenuManager menuManager;
    private PlaylistMenu menu;
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
        menu = new PlaylistMenu(mockController, menuManager);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testOption1_PlaylistCreationMenu() {
        when(mockController.currentUserHasLibrary()).thenReturn(true);
        simulate("1\n");
        menu.handleInput();
        assertTrue(menuManager.getCurrentMenu() instanceof PlaylistCreationMenu);
    }

    @Test
    void testOption2_SetPublic() {
        when(mockController.currentUserHasLibrary()).thenReturn(true);
        when(mockController.listUserPlaylists()).thenReturn("pl1");
        when(mockController.setPlaylistAsPublic(5)).thenReturn("public ok");
        simulate("2\n5\n");
        menu.handleInput();
        assertTrue(out.toString().contains("public ok"));
    }

    @Test
    void testOption3_AddMusic() {
        when(mockController.currentUserHasLibrary()).thenReturn(true);
        when(mockController.listUserPlaylists()).thenReturn("pl1");
        when(mockController.listAllMusics()).thenReturn("m1,m2");
        when(mockController.addMusicToCurrentUserPlaylist(2, "song")).thenReturn("added ok");
        simulate("3\n2\nsong\n");
        menu.handleInput();
        assertTrue(out.toString().contains("added ok"));
    }

    @Test
    void testOption4_RemoveMusic() {
        when(mockController.currentUserHasLibrary()).thenReturn(true);
        when(mockController.listUserPlaylists()).thenReturn("pl1");
        when(mockController.listPlaylistMusics(3)).thenReturn("m1");
        when(mockController.removeMusicFromPlaylist("m1", 3)).thenReturn("removed ok");
        simulate("4\n3\nm1\n");
        menu.handleInput();
        assertTrue(out.toString().contains("removed ok"));
    }

    @Test
    void testOption5_ListPlaylists() {
        when(mockController.currentUserHasLibrary()).thenReturn(true);
        when(mockController.listUserPlaylists()).thenReturn("list ok");
        simulate("5\n");
        menu.handleInput();
        assertTrue(out.toString().contains("list ok"));
    }

    @Test
    void testOption6_AddPublicPlaylist() {
        when(mockController.currentUserHasLibrary()).thenReturn(true);
        when(mockController.listPublicPlaylists()).thenReturn("pub1");
        when(mockController.addPublicPlaylistToLibrary(7)).thenReturn("add pub ok");
        simulate("6\n7\n");
        menu.handleInput();
        assertTrue(out.toString().contains("add pub ok"));
    }

    @Test
    void testOption0_BackToMain() {
        when(mockController.currentUserHasLibrary()).thenReturn(true);
        simulate("0\n");
        menu.handleInput();
        assertTrue(menuManager.getCurrentMenu() instanceof MainMenu);
    }

    @Test
    void testInvalidOption() {
        when(mockController.currentUserHasLibrary()).thenReturn(true);
        simulate("99\n");
        menu.handleInput();
        assertTrue(out.toString().contains("Opção inválida"));
    }

    @Test
    void testNonNumericInput() {
        when(mockController.currentUserHasLibrary()).thenReturn(true);
        simulate("x\n");
        menu.handleInput();
        assertTrue(out.toString().toLowerCase().contains("insira apenas números"));
    }

    private void simulate(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        menu.setScanner(new Scanner(System.in));
    }
}