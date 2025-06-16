package org.Exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmptyPlaylistExceptionTest {
    @Test
    void testMessageRoundtrip() {
        String msg = "Playlist is empty";
        EmptyPlaylistException ex = new EmptyPlaylistException(msg);
        assertEquals(msg, ex.getMessage());
    }
}