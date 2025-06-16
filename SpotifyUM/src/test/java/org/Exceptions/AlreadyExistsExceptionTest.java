package org.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AlreadyExistsException
 */
public class AlreadyExistsExceptionTest {
    @Test
    void testMessageConstruction() {
        AlreadyExistsException ex = new AlreadyExistsException("Resource");
        assertEquals("Já existe: Resource", ex.getMessage());
    }
}