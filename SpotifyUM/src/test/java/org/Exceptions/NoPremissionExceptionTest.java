package org.Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoPremissionExceptionTest {
    @Test
    void testMessageConstruction() {
        NoPremissionException ex = new NoPremissionException("RestrictedArea");
        assertEquals("Não tem permissão para aceder a este recurso: RestrictedArea", ex.getMessage());
    }
}