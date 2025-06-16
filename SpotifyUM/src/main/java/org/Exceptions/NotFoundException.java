package org.Exceptions;

/**
 * Exceção para indicar que um recurso não foi encontrado.
 */
public class NotFoundException extends Exception {
    /**
     * Mensagem de erro.
     */
    private String message;

    /**
     * Construtor da exceção.
     * @param message Mensagem adicional sobre o recurso não encontrado.
     */
    public NotFoundException(String message) {
        this.message = "Não encontrado: " + message;
    }

    /**
     * Obtém a mensagem de erro.
     * @return Mensagem de erro.
     */
    public String getMessage() {
        return message;
    }
    
}
