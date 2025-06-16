package org.Model.Music;

/**
 *
 * Herda todas as propriedades de Music e adiciona um campo URL para recursos multimídia.
 */
public class MusicMultimedia extends Music {
    /**
     * URL para o conteúdo multimídia.
     */
    private String url;

    /**
     * Construtor vazio da classe MusicMultimedia.
     */
    public MusicMultimedia() {
        super();
        this.url = "";
    }
    /**
     * Construtor da classe MusicMultimedia.
     * @param name Nome da música.
     * @param interpreter Intérprete/artista.
     * @param publisher Editora da música.
     * @param lyrics Letra da música.
     * @param musicalFigures Figuras musicais ou notação.
     * @param musicGenre Gênero da música.
     * @param album Nome do álbum.
     * @param duration Duração em segundos.
     * @param explicit Se a música é explícita.
     * @param url URL para o conteúdo multimídia.
     */
    public MusicMultimedia(String name, String interpreter, String publisher, String lyrics,String musicalFigures, String musicGenre, String album, int duration, boolean explicit,String url) {
        super(name, interpreter, publisher, lyrics, musicalFigures, musicGenre, album, duration, explicit);
        this.url = url;
    }

    /**
     * Construtor de cópia da classe MusicMultimedia.
     * @param m Objeto MusicMultimedia a ser copiado.
     */
    public MusicMultimedia(MusicMultimedia m) {
        super(m);
        this.url = m.getUrl();
    }

    /**
     * Obtém a URL.
     * @return String da URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Define a URL.
     * @param url String da URL a ser definida.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Retorna uma representação em String do objeto MusicMultimedia.
     * @return String descrevendo o objeto.
     */
    public String toString() {
        return "MusicaMultimedia{" +
                super.toString() +
                ", url='" + url + '\'' +
                '}';
    }

    /**
     * Compara dois objetos MusicMultimedia.
     * @param o Objeto a ser comparado.
     * @return true se igual, false caso contrário.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicMultimedia)) return false;
        if (!super.equals(o)) return false;

        MusicMultimedia that = (MusicMultimedia) o;

        return url != null ? url.equals(that.url) : that.url == null;
    }
    
    /**
     * Clona o objeto MusicMultimedia.
     * @return Novo objeto MusicMultimedia com os mesmos dados.
     */
    public MusicMultimedia clone() {
        return new MusicMultimedia(this);
    }

}
