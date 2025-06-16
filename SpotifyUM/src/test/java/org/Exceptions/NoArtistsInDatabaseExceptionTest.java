package org.Exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NoArtistsInDatabaseExceptionTest {
    @Test
    void testMessageIgnoredParameter() {
        NoArtistsInDatabaseException ex = new NoArtistsInDatabaseException("ignored");
        assertEquals("Não existem artistas na base de dados.", ex.getMessage());
    }
}