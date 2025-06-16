package org.Exceptions;

/**
 * Exceção para indicar que não existem músicas na base de dados.
 */
public class NoMusicsInDatabaseException extends Exception {
    /**
     * Mensagem de erro.
     */
    private String message;

    /**
     * Construtor da exceção.
     * @param message Mensagem de erro (ignorada, mensagem fixa em português).
     */
    public NoMusicsInDatabaseException(String message) {
        this.message = "Não existem músicas na base de dados.";
    }

    /**
     * Obtém a mensagem de erro.
     * @return Mensagem de erro.
     */
    public String getMessage() {
        return message;
    }
}
