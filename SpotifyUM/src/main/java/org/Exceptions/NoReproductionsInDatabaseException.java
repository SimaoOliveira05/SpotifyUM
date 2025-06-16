package org.Exceptions;

/**
 * Exceção para indicar que não existem reproduções na base de dados.
 */
public class NoReproductionsInDatabaseException extends Exception {
    /**
     * Mensagem de erro.
     */
    private String message;

    /**
     * Construtor da exceção.
     */
    public NoReproductionsInDatabaseException() {
        this.message = "Não existem reproduções na base de dados.";
    }

    /**
     * Obtém a mensagem de erro.
     * @return Mensagem de erro.
     */
    public String getMessage() {
        return message;
    }
    
}
