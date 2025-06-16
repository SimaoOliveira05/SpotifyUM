package org.Model;

import org.Model.Playlist.PlaylistFavorites;
import org.Model.Playlist.PlaylistRandom;
import org.Model.Music.Music;
import org.Model.Album.Album;
import org.Model.User.User;
import org.Model.Playlist.Playlist;
import org.Exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SpotifUMTest {

    private SpotifUM model;

    @BeforeEach
    void setUp() {
        // uses populateDatabase()
        model = new SpotifUM();
    }


    @Test
    void testAddNewUserAndExists() {
        assertFalse(model.userExists("alice"));
        model.addNewUser("alice","a@b.com","addr","pw");
        assertTrue(model.userExists("alice"));
    }

    @Test
    void testAuthenticateUserSuccessAndFailure() {
        model.addNewUser("bob","b@c.com","addr","secret");
        assertThrows(NotFoundException.class,
                     () -> model.authenticateUser("noone","x"));
        assertThrows(UnsupportedOperationException.class,
                     () -> model.authenticateUser("bob","wrong"));
        try {
            model.authenticateUser("bob","secret");
        } catch (UnsupportedOperationException e) {
            fail("User should be authenticated");
        } catch (NotFoundException e) {
            fail("User not found");
        }

        assertEquals("bob", model.getCurrentUser().getUsername());
    }


    @Test
    void testAlbumCRUD() throws Exception {
        assertFalse(model.albumExists("MyAlbum"));
        model.addNewAlbum("MyAlbum","me");
        assertTrue(model.albumExists("MyAlbum"));
        Album a = model.getAlbumByName("MyAlbum");
        assertEquals("MyAlbum", a.getName());
        assertThrows(NotFoundException.class, () -> model.getAlbumByName("xxx"));
    }



    @Test
    void testCopyAndEquals() {
        // create a minimal custom instance
        Map<String, Music> m = new HashMap<>();
        Map<Integer, Playlist> p = new HashMap<>();
        Map<String, User> u = new HashMap<>();
        Map<String, Album> a = new HashMap<>();
        Map<String, Integer> art = new HashMap<>();
        Map<String, Integer> gen = new HashMap<>();
        SpotifUM base = new SpotifUM(m,p,u,a,art,gen);
        SpotifUM copy = new SpotifUM(base);
        assertEquals(base, copy);
        assertNotSame(base, copy);
        assertEquals("SpotifUM(...)", base.toString());
    }

    @Test
    void testFavoritesAndRandomPlaylists() {
        model.addNewUser("u","e","a","pw");
        try {
            model.authenticateUser("u","pw");
        } catch (NotFoundException e) {
            fail("User not found");
        } catch (UnsupportedOperationException e) {
            fail("User not authenticated");
        }
        // no reproductions yet → empty favorites
        PlaylistFavorites fav = model.createFavoritesPlaylist(1000, false);
        assertNotNull(fav, "Favorites playlist should not be null");
        assertTrue(fav.getMusics().isEmpty(), "Favorites playlist should be empty initially");
        PlaylistRandom rnd = model.getRandomPlaylist();
        assertNotNull(rnd, "Random playlist should not be null");
        assertNotNull(rnd.getMusics(), "Random playlist musics should not be null");
    }

    @Test
    void testDateBasedUserReproductionsEmpty() {
        assertThrows(NoUsersInDatabaseException.class,
                     () -> model.getUserWithMostReproductions(LocalDate.now(), LocalDate.now()));
    }
}