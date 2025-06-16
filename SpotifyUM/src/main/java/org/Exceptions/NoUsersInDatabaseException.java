package org.Exceptions;

/**
 * Exceção para indicar que não existem utilizadores na base de dados.
 */
public class NoUsersInDatabaseException extends Exception {
    /**
     * Mensagem de erro.
     */
    private String message;

    /**
     * Construtor da exceção.
     * @param message Mensagem de erro (ignorada, mensagem fixa em português).
     */
    public NoUsersInDatabaseException(String message) {
        this.message = "Não existem utilizadores na base de dados.";
    }

    /**
     * Obtém a mensagem de erro.
     * @return Mensagem de erro.
     */
    public String getMessage() {
        return message;
    }
    
}
