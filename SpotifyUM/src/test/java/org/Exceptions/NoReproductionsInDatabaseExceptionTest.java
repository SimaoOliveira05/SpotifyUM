package org.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoReproductionsInDatabaseExceptionTest {
    @Test
    void testDefaultConstructorMessage() {
        NoReproductionsInDatabaseException ex = new NoReproductionsInDatabaseException();
        assertEquals("Não existem reproduções na base de dados.", ex.getMessage());
    }
}