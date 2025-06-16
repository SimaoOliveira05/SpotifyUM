package org.Model.Music;
import java.time.LocalDate;
import java.io.Serializable;
/**
 *
 * Contém a música reproduzida e a data da reprodução.
 */
public class MusicReproduction implements Serializable {
    /**
     * Música que foi reproduzida.
     */
    private Music music;
    /**
     * Data em que a música foi reproduzida.
     */
    private LocalDate date;

    /**
     * Construtor da classe MusicReproduction.
     * @param music Música que foi reproduzida.
     */
    public MusicReproduction(Music music) {
        this.music = music;
        this.date = LocalDate.now();
    }

    /**
     * Construtor da classe MusicReproduction.
     * @param music Música que foi reproduzida.
     * @param date Data da reprodução.
     */
    public MusicReproduction(Music music, LocalDate date) {
        this.music = music;
        this.date = date;
    }

    /**
     * Construtor por cópia da classe MusicReproduction
     * @param musicReproduction Objeto MusicReproduction a ser copiado.
     */
    public MusicReproduction(MusicReproduction musicReproduction) {
        this.music = musicReproduction.getMusic();
        this.date = musicReproduction.getDate();
    }

    /**
     * Obtém a música reproduzida.
     * @return Objeto Music.
     */
    public Music getMusic() {
        return music;
    }

    /**
     * Define a música reproduzida.
     * @param music Objeto Music a ser definido.
     */
    public void setMusic(Music music) {
        this.music = music;
    }

    /**
     * Obtém a data da reprodução.
     * @return Data em que a música foi reproduzida.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Define a data da reprodução.
     * @param date Data a ser definida.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Compara dois objetos MusicReproduction.
     * @return Novo objeto MusicReproduction com os mesmos dados.
     */
    public MusicReproduction clone(){
        return new MusicReproduction(this);
    }

    /**
     * Retorna uma representação em String do objeto MusicReproduction.
     * @return String descrevendo a música e a data.
     */
    public String toString() {
        return "MusicReproduction{" +
                "music =" + music +
                ",date =" + date +
                '}';
    }
}
