package org.Exceptions;

/**
 * Exceção para indicar que um recurso já existe.
 */
public class AlreadyExistsException extends Exception {
    /**
     * Mensagem de erro.
     */
    private String message;

    /**
     * Construtor da exceção.
     * @param message Mensagem adicional sobre o recurso existente.
     */
    public AlreadyExistsException(String message) {
        this.message = "Já existe: " + message;
    }

    /**
     * Obtém a mensagem de erro.
     * @return Mensagem de erro.
     */
    public String getMessage() {
        return message;
    }
}
