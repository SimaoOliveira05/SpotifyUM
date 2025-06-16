package org.Model.Playlist;

import java.util.List;

import org.Model.Music.Music;

/**
 *
 * Representa uma playlist especial de músicas favoritas do utilizador no sistema SpotifUM.
 * Esta classe herda de Playlist e é utilizada para criar playlists automáticas com as músicas favoritas do utilizador.
 * Permite criar, clonar e comparar playlists de favoritos.
 *
 */
public class PlaylistFavorites extends Playlist {
    /**
     * Construtor que cria uma playlist de favoritos com uma lista de músicas.
     * @param music Lista de músicas favoritas
     */
    public PlaylistFavorites(List<Music> music) {
        super("Feito para Você", "SpotifyUM", music);
    }

    /**
     * Construtor vazio.
     */
    public PlaylistFavorites(){
        super();
    }

    /**
     * Construtor de cópia a partir de uma Playlist genérica.
     * @param p Playlist a ser copiada
     */
    public PlaylistFavorites(Playlist p) {
        super(p);
    }

    /**
     * Retorna uma representação textual da playlist de favoritos.
     * @return String com informações da playlist
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist Favoritos{");
        sb.append("nome='").append(getName()).append('\'');
        sb.append(", autor='").append(getAutor()).append('\'');
        sb.append(", musicas=").append(getMusics());
        sb.append('}');
        return sb.toString();
    }

    /**
     * Cria uma cópia (clone) da playlist de favoritos.
     * @return Nova instância de PlaylistRandom baseada nesta playlist
     */
    public PlaylistRandom clone() {
        return new PlaylistRandom(this);
    }

    /**
     * Compara se duas playlists de favoritos são iguais.
     * @param o Objeto a comparar
     * @return true se forem iguais, false caso contrário
     */
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaylistFavorites)) return false;
        PlaylistFavorites that = (PlaylistFavorites) o;
        return super.equals(that);
    }
    
    
}