package org.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoUsersInDatabaseExceptionTest {
    @Test
    void testMessageIgnoredParameter() {
        NoUsersInDatabaseException ex = new NoUsersInDatabaseException("ignored");
        assertEquals("Não existem utilizadores na base de dados.", ex.getMessage());
    }
}