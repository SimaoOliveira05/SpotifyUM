package org.Model.User;

import org.Exceptions.AlreadyExistsException;
import org.Exceptions.NotFoundException;
import org.Exceptions.NoPremissionException;
import org.Model.Music.Music;
import org.Model.Plan.PlanFree;
import org.Model.Playlist.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;


class UserTest {

    private User user;

    private Music music;
    private final String NAME = "Bohemian Rhapsody";
    private final String INTERPRETER = "Queen";
    private final String PUBLISHER = "EMI";
    private final String LYRICS = "Is this the real life? Is this just fantasy?";
    private final String MUSICAL_FIGURES = "A-B-C-D";
    private final String GENRE = "Rock";
    private final String ALBUM = "A Night at the Opera";
    private final int DURATION = 355;
    private final boolean EXPLICIT = false;
    
    @BeforeEach
    public void setUp() {
        // Make sure the music object is properly initialized
        music = new Music(NAME, INTERPRETER, PUBLISHER, LYRICS, MUSICAL_FIGURES, 
                          GENRE, ALBUM, DURATION, EXPLICIT);
        user = new User("testUser", "test@example.com", "123 Test St", "password123");
    }

    @Test
    void testUserConstructor() {
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("123 Test St", user.getAdress());
        assertEquals("password123", user.getPassword());
        assertTrue(user.getPlan() instanceof PlanFree);
        assertTrue(user.getPlaylists().isEmpty());
        assertTrue(user.getMusicReproductions().isEmpty());
    }

    @Test
    void testSetAndGetUsername() {
        user.setUsername("newUser");
        assertEquals("newUser", user.getUsername());
    }

    @Test
    void testSetAndGetEmail() {
        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());
    }

    @Test
    void testSetAndGetAdress() {
        user.setAdress("456 New St");
        assertEquals("456 New St", user.getAdress());
    }

    @Test
    void testSetAndGetPassword() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void testAddPlaylist() {
        user.setPlan(new PlanFree() {
            @Override
            public boolean canAccessLibrary() {
                return true;
            }
        });

        user.addPlaylist("My Playlist");
        assertEquals(1, user.getPlaylists().size());
        assertEquals("My Playlist", user.getPlaylists().get(0).getName());
    }

    @Test
    void testAddPlaylistThrowsExceptionForFreeUser() {
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> user.addPlaylist("My Playlist"));
        assertEquals("Utilizadores Free não podem adicionar playlists.", exception.getMessage());
    }

    @Test
    void testAddMusicReproduction() {
        user.addMusicReproduction(music);
        assertEquals(1, user.getMusicReproductions().size());
        assertEquals(music, user.getMusicReproductions().get(0).getMusic());
    }

    @Test
    void testAddMusicToPlaylist() throws NotFoundException, AlreadyExistsException, NoPremissionException {
        user.setPlan(new PlanFree() {
            @Override
            public boolean canAccessLibrary() {
                return true;
            }
        });

        Playlist playlist = new Playlist("My Playlist", user.getUsername());
        user.addPlaylistToLibrary(playlist);

        user.addMusicPlaylist(playlist.getId(), music);

        Playlist retrievedPlaylist = user.getPlaylistById(playlist.getId());
        assertTrue(retrievedPlaylist.getMusics().contains(music));
    }

    @Test
    void testAddMusicToPlaylistThrowsNotFoundException() {
        Exception exception = assertThrows(NotFoundException.class, () -> user.addMusicPlaylist(1, music));
        assertEquals("Não encontrado: 1", exception.getMessage());
    }

    @Test
    void testRemoveMusicFromPlaylist() throws NotFoundException, NoPremissionException {
        user.setPlan(new PlanFree() {
            @Override
            public boolean canAccessLibrary() {
                return true;
            }
        });

        Playlist playlist = new Playlist("My Playlist", user.getUsername());
        try {
            user.addPlaylistToLibrary(playlist);
            playlist.addMusic(music);
        } catch (AlreadyExistsException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }

        user.removeMusicFromPlaylist(music, playlist.getId());
        assertFalse(playlist.getMusics().contains(music));
    }

    @Test
    void testRemoveMusicFromPlaylistThrowsNotFoundException() {
        Exception exception = assertThrows(NotFoundException.class, () -> user.removeMusicFromPlaylist(music, 1));
        assertEquals("Não encontrado: 1", exception.getMessage());
    }

    @Test
    void testGetMusicReproductionsCount() {
        user.addMusicReproduction(music);

        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(1);

        assertEquals(1, user.getMusicReproductionsCount(startDate, endDate));
    }

    @Test
    void testClone() {
        User clonedUser = user.clone();
        assertEquals(user, clonedUser);
        assertNotSame(user, clonedUser);
    }
}