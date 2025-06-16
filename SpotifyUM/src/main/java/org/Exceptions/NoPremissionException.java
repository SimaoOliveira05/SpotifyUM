package org.Exceptions;


/**
 * Exceção para indicar falta de permissão de acesso.
 */
public class NoPremissionException extends Exception {
    /**
     * Mensagem de erro.
     */
    private String message;

    /**
     * Construtor da exceção.
     * @param message Mensagem adicional sobre o recurso sem permissão.
     */
    public NoPremissionException(String message) {
        this.message = "Não tem permissão para aceder a este recurso: " + message;
    }

    /**
     * Obtém a mensagem de erro.
     * @return Mensagem de erro.
     */
    public String getMessage() {
        return message;
    }
}
