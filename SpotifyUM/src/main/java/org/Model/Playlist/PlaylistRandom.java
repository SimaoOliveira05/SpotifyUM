package org.Model.Playlist;

import java.util.List;

import org.Model.Music.Music;

/**
 *
 * Representa uma playlist aleatória de músicas no sistema SpotifUM.
 * Esta classe herda de Playlist e é utilizada para criar playlists automáticas com músicas selecionadas aleatoriamente.
 * Permite criar, clonar e comparar playlists aleatórias.
 *
 */
public class PlaylistRandom extends Playlist {
    /**
     * Construtor que cria uma playlist aleatória com um nome e uma lista de músicas.
     * @param nome Nome da playlist
     * @param music Lista de músicas aleatórias
     */
    public PlaylistRandom(String nome, List<Music> music) {
        super(nome, "SpotifyUM", music);
    }

    /**
     * Construtor vazio.
     */
    public PlaylistRandom(){
        super();
    }

    /**
     * Construtor de cópia a partir de uma Playlist genérica.
     * @param p Playlist a ser copiada
     */
    public PlaylistRandom(Playlist p) {
        super(p);
    }

    /**
     * Retorna uma representação textual da playlist aleatória.
     * @return String com informações da playlist
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist Aleatória{");
        sb.append("nome='").append(getName()).append('\'');
        sb.append(", autor='").append(getAutor()).append('\'');
        sb.append(", musicas=").append(getMusics());
        sb.append('}');
        return sb.toString();
    }

    /**
     * Cria uma cópia (clone) da playlist aleatória.
     * @return Nova instância de PlaylistRandom baseada nesta playlist
     */
    public PlaylistRandom clone() {
        return new PlaylistRandom(this);
    }

    /**
     * Compara se duas playlists aleatórias são iguais.
     * @param o Objeto a comparar
     * @return true se forem iguais, false caso contrário
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaylistRandom)) return false;
        PlaylistRandom that = (PlaylistRandom) o;
        return super.equals(that);
    }
    
    
}
