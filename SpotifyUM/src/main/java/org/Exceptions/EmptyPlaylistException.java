package org.Exceptions;

/**
 * Exceção para indicar que uma playlist está vazia.
 */
public class EmptyPlaylistException extends Exception {
    /**
     * Mensagem de erro.
     */
    private String message;

    /**
     * Construtor da exceção.
     * @param message Mensagem de erro.
     */
    public EmptyPlaylistException(String message) {
        this.message =  message;
    }

    /**
     * Obtém a mensagem de erro.
     * @return Mensagem de erro.
     */
    public String getMessage() {
        return message;
    }
}
