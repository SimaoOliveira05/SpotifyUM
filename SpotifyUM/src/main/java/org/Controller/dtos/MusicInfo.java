package org.Controller.dtos;

/**
 *
 * Esta classe é utilizada para transferir dados de uma música entre camadas da aplicação,
 * incluindo nome, artista, letra, se é explícita, URL e mensagens de erro.
 */

/**
 * Classe que representa informações detalhadas de uma música.
 */
public class MusicInfo {
    /** Nome da música. */
    private String musicName;
    /** Nome do artista. */
    private String artistName;
    /** Letra da música, separada por palavras. */
    private String[] lyrics;
    /** Indica se a música é explícita. */
    private boolean isExplicit;
    /** URL associada à música (caso exista). */
    private String url;
    /** Mensagem de erro, caso exista algum problema. */
    private String errorMessage;

    /**
     * Construtor principal.
     * @param musicName Nome da música.
     * @param artistName Nome do artista.
     * @param lyrics Letra da música (string completa).
     * @param url URL da música.
     * @param isExplicit Indica se a música é explícita.
     */
    public MusicInfo(String musicName, String artistName, String lyrics, String url, boolean isExplicit) {
        this.musicName = musicName;
        this.artistName = artistName;
        this.lyrics = lyrics.split(" ");
        this.isExplicit = isExplicit;
        this.url = url;
        errorMessage = "";
    }

    /**
     * Construtor para situações de erro.
     * @param errorMessage Mensagem de erro.
     */
    public MusicInfo(String errorMessage){
        this.musicName = "";
        this.artistName = "";
        this.lyrics = null;
        this.isExplicit = false;
        this.url = "";
        this.errorMessage = errorMessage;
    }

    /**
     * Obtém o nome da música.
     * @return Nome da música.
     */
    public String getMusicName(){
        return this.musicName;
    }

    /**
     * Obtém o nome do artista.
     * @return Nome do artista.
     */
    public String getArtistName(){
        return this.artistName;
    }

    /**
     * Obtém a letra da música (array de palavras).
     * @return Array de palavras da letra.
     */
    public String[] getLyrics(){
        return this.lyrics;
    }

    /**
     * Indica se a música é explícita.
     * @return true se for explícita, false caso contrário.
     */
    public boolean isExplicit(){
        return this.isExplicit;
    }

    /**
     * Obtém a URL da música.
     * @return URL da música.
     */
    public String getUrl(){
        return this.url;
    }

    /**
     * Obtém a mensagem de erro, se existir.
     * @return Mensagem de erro.
     */
    public String getErrorMessage(){
        return this.errorMessage;
    }

    /**
     * Define a mensagem de erro.
     * @param error Mensagem de erro a definir.
     */
    public void setErrorMessage(String error){
        this.errorMessage = error;
    }
}
