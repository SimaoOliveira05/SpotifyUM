package org.Exceptions;

/**
 * Exceção para indicar que não existem artistas na base de dados.
 */
public class NoArtistsInDatabaseException extends Exception {
    /**
     * Mensagem de erro.
     */
    private String message;

    /**
     * Construtor da exceção.
     * @param message Mensagem de erro (ignorada, mensagem fixa em português).
     */
    public NoArtistsInDatabaseException(String message) {
        this.message = "Não existem artistas na base de dados.";
    }

    /**
     * Obtém a mensagem de erro.
     * @return Mensagem de erro.
     */
    public String getMessage() {
        return message;
    }
    
}
