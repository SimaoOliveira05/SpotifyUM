package org.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoMusicsInDatabaseExceptionTest {
    @Test
    void testMessageIgnoredParameter() {
        NoMusicsInDatabaseException ex = new NoMusicsInDatabaseException("ignored");
        assertEquals("Não existem músicas na base de dados.", ex.getMessage());
    }
}