package org.Exceptions;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotFoundExceptionTest {
    @Test
    void testMessageConstruction() {
        NotFoundException ex = new NotFoundException("Item");
        assertEquals("Não encontrado: Item", ex.getMessage());
    }
}