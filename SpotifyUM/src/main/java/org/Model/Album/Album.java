package org.Model.Album;
import java.io.Serializable;
import java.util.List;

import org.Model.Music.Music;
import java.util.ArrayList;

/**
 * Representa um álbum musical.
 */
public class Album implements Serializable{
    /**
     * Nome do álbum.
     */
    private String name;
    /**
     * Nome do artista.
     */
    private String artist;
    /**
     * Lista de faixas musicais do álbum.
     */
    private List<Music> musics;

    /**
     * Construtor da classe Album.
     *
     * @param name Nome do álbum.
     * @param artist Nome do artista.
     * @param musics Lista de faixas musicais.
     */
    public Album(String name, String artist, List<Music> musics) {
        this.name = name;
        this.artist = artist;
        this.musics = new ArrayList<Music>();
        for (Music m : musics) {
            this.musics.add(m.clone());
        }
    }

    /**
     * Construtor vazio da classe Album.
     */
    public Album() {
        this.name = "";
        this.artist = "";
        this.musics = null;
    }

    /**
     * Construtor de cópia da classe Album.
     *
     * @param album Objeto Album a ser copiado.
     */
    public Album(Album album) {
        this.name = album.getName();
        this.artist = album.getArtist();
        this.musics = album.getMusics(); // Faz uma cópia profunda da lista de músicas
    }

    /**
     * Construtor da classe Album.
     *
     * @param name Nome do álbum.
     * @param artist Nome do artista.
     */
    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.musics = new ArrayList<Music>();
    }

    /**
     * Retorna o nome do álbum.
     * @return Nome do álbum.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do álbum.
     * @param name Nome do álbum.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o nome do artista.
     * @return Nome do artista.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Define o nome do artista.
     * @param artist Nome do artista.
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Retorna a lista de faixas musicais.
     * @return Lista de faixas musicais.
     */
    public List<Music> getMusics() {
        List<Music> musicList = new ArrayList<>();
        for (Music m : this.musics) {
            musicList.add(m.clone());
        }
        return musicList;
    }

    /**
     * Define a lista de faixas musicais.
     * @param musics Lista de faixas musicais.
     */
    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    /**
     * Método clone que cria uma cópia do objeto Album.
     * @return Novo objeto Album com os mesmos dados.
     */
    public Album clone() {
        return new Album(this);
    }

    /**
     * Método toString que retorna uma representação em string do objeto Album.
     * @return String descrevendo o álbum e suas faixas.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Album: ").append(name).append("\n");
        sb.append("Artista: ").append(artist).append("\n");
        sb.append("Músicas:\n");
        for (Music music : musics) {
            sb.append(music.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Adiciona uma faixa musical ao álbum.
     * @param music Faixa musical a ser adicionada.
     */
    public void addMusic(Music music) {
        this.musics.add(music);
    }

    /**
     * Remove uma faixa musical do álbum.
     * @param music Faixa musical a ser removida.
     */
    public void removeMusic(Music music) {
        this.musics.remove(music);
    }

    /**
     * Retorna uma faixa musical pelo nome.
     * @param name Nome da faixa musical a ser pesquisada.
     * @return Faixa musical correspondente ao nome fornecido.
     */
    public Music getMusicByName(String name){
        for (Music music : musics) {
            if (music.getName().equals(name)) {
                return music;
            }
        }
        return null;
    }


}
