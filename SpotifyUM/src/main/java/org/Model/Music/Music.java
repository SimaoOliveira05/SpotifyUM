package org.Model.Music;

import java.io.Serializable;

/**
 * Representa uma faixa musical.
 * Contém informações como nome, intérprete, editora, letra, figuras musicais,
 */
public class Music implements Serializable {
    /**
     * Nome da faixa musical.
     */
    private String name;
    /**
     * Intérprete/artista da faixa.
     */
    private String interpreter;
    /**
     * Editora da faixa.
     */
    private String publisher;
    /**
     * Letra da faixa.
     */
    private String lyrics;
    /**
     * Figuras musicais ou notação.
     */
    private String musicalFigures;
    /**
     * Gênero musical da faixa.
     */
    private String genre;
    /**
     * Nome do álbum.
     */
    private String album;
    /**
     * Duração da faixa em segundos.
     */
    private int duration;
    /**
     * Número de reproduções da faixa.
     */
    private int reproductions;
    /**
     * Indica se a faixa é explícita.
     */
    private boolean explicit;

    /**
     * Construtor vazio da classe Music.
     */
    public Music() {
        this.name = "";
        this.interpreter = "";
        this.publisher = "";
        this.lyrics = "";
        this.musicalFigures = "";
        this.genre = "";
        this.album = "";
        this.duration = 0;
        this.reproductions = 0; // Sempre com 0
        this.explicit = false; // Por padrão, não é explícita
    }
    /**
     * Construtor da classe Music.
     * @param name Nome da música.
     * @param interpreter Intérprete/artista.
     * @param publisher Editora da música.
     * @param lyrics Letra da música.
     * @param musicalFigures Figuras musicais ou notação.
     * @param musicGenre Gênero da música.
     * @param album Nome do álbum.
     * @param duration Duração em segundos.
     * @param explicit Se a música é explícita.
     */
    public Music(String name, String interpreter, String publisher, String lyrics,
                 String musicalFigures, String musicGenre, String album, int duration, boolean explicit) {
        this.name = name;
        this.interpreter = interpreter;
        this.publisher = publisher;
        this.lyrics = lyrics;
        this.musicalFigures = musicalFigures;
        this.genre = musicGenre;
        this.album = album;
        this.duration = duration;
        this.reproductions = 0; // Sempre começa com 0
        this.explicit = explicit; // Pode ser explícita ou não
    }

    /**
     * Construtor de cópia da classe Music.
     * @param m Objeto Music a ser copiado.
     */
    public Music(Music m) {
        this.name = m.getName();
        this.interpreter = m.getInterpreter();
        this.publisher = m.getPublisher();
        this.lyrics = m.getLyrics();
        this.musicalFigures = m.getMusicalFigures();
        this.genre = m.getGenre();
        this.album = m.getAlbum();
        this.duration = m.getDuration();
        this.reproductions = m.getReproductions();
        this.explicit = m.isExplicit(); // Copia o estado explícito
    }


    // Getters e Setters
    /**
     * Retorna o nome da faixa.
     * @return Nome da faixa.
     */
    public String getName() {
        return name;
    }
    /**
     * Define o nome da faixa.
     * @param nome Nome a ser definido.
     */
    public void setName(String nome) {
        this.name = nome;
    }
    /**
     * Retorna o intérprete da faixa.
     * @return Intérprete da faixa.
     */
    public String getInterpreter() {
        return interpreter;
    }
    /**
     * Define o intérprete da faixa.
     * @param intreprete Intérprete a ser definido.
     */
    public void setInterpreter(String intreprete) {
        this.interpreter = intreprete;
    }
    /**
     * Retorna a editora da faixa.
     * @return Editora da faixa.
     */
    public String getPublisher() {
        return publisher;
    }
    /**
     * Define a editora da faixa.
     * @param editora Editora a ser definida.
     */
    public void setPublisher(String editora) {
        this.publisher = editora;
    }
    /**
     * Retorna a letra da faixa.
     * @return Letra da faixa.
     */
    public String getLyrics() {
        return lyrics;
    }
    /**
     * Define a letra da faixa.
     * @param letraPoema Letra a ser definida.
     */
    public void setLyrics(String letraPoema) {
        this.lyrics = letraPoema;
    }
    /**
     * Retorna as figuras musicais.
     * @return Figuras musicais.
     */
    public String getMusicalFigures() {
        return musicalFigures;
    }
    /**
     * Define as figuras musicais.
     * @param linhasMusicais Figuras musicais a serem definidas.
     */
    public void setMusicalFigures(String linhasMusicais) {
        this.musicalFigures = linhasMusicais;
    }
    /**
     * Retorna o gênero da faixa.
     * @return Gênero da faixa.
     */
    public String getGenre() {
        return genre;
    }
    /**
     * Define o gênero da faixa.
     * @param generoMusical Gênero a ser definido.
     */
    public void setGenre(String generoMusical) {
        this.genre = generoMusical;
    }
    /**
     * Retorna a duração em segundos.
     * @return Duração em segundos.
     */
    public int getDuration() {
        return duration;
    }
    /**
     * Define a duração em segundos.
     * @param duracao Duração a ser definida.
     */
    public void setDuration(int duracao) {
        this.duration = duracao;
    }
    /**
     * Retorna o nome do álbum.
     * @return Nome do álbum.
     */
    public String getAlbum() {
        return album;
    }
    /**
     * Define o nome do álbum.
     * @param album Nome do álbum a ser definido.
     */
    public void setAlbum(String album) {
        this.album = album;
    }
    /**
     * Retorna o número de reproduções.
     * @return Número de vezes que a faixa foi reproduzida.
     */
    public int getReproductions() {
        return reproductions;
    }
    /**
     * Define o número de reproduções.
     * @param reproducoes Número de reproduções a ser definido.
     */
    public void setReproductions(int reproducoes) {
        this.reproductions = reproducoes;
    }
    /**
     * Retorna se a faixa é explícita.
     * @return true se for explícita, false caso contrário.
     */
    public boolean isExplicit() {
        return explicit;
    }
    /**
     * Define se a faixa é explícita.
     * @param explicit true se for explícita, false caso contrário.
     */
    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    /**
     * Método clone que cria uma cópia do objeto Music.
     * @return Novo objeto Music com os mesmos dados.
     */
    public Music clone(){
        return new Music(this);
    }

    /**
     * Método toString que retorna uma representação em string do objeto Music.
     * @return String descrevendo a faixa musical.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(this.name).append("\n");
        sb.append("Interprete: ").append(this.interpreter).append("\n");
        sb.append("Editora: ").append(this.publisher).append("\n");
        sb.append("Letra: ").append(this.lyrics).append("\n");
        sb.append("Linhas Musicais: ").append(this.musicalFigures).append("\n");
        sb.append("Genero Musical: ").append(this.genre).append("\n");
        sb.append("Album: ").append(this.album).append("\n");
        sb.append("Duracao: ").append(this.duration).append("\n");
        sb.append("Reproducoes: ").append(this.reproductions).append("\n");
        sb.append("Explicita: ").append(this.explicit ? "Sim" : "Nao").append("\n");
        return sb.toString();
    }

    /**
     * Compara dois objetos Music.
     * @param o Objeto a ser comparado.
     * @return true se igual, false caso contrário.
     */
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Music)) return false;

        Music that = (Music) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    /**
     * Reproduz a faixa musical.
     * @return Letra da faixa musical.
     */
    public String play(){
        this.reproductions++;
        return this.lyrics;
    }

}

