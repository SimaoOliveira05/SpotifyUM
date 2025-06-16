package org.Model.Playlist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.Exceptions.AlreadyExistsException;
import org.Model.Music.Music;

/**
 *
 * Cada playlist possui um identificador único, nome, autor e uma lista de músicas.
 * Permite operações como adicionar/remover músicas, clonar, comparar e obter informações da playlist.
 */
public class Playlist implements Serializable{
    /**
     * Serialização da classe Playlist.
     */
    private static int idCounter = 0;

    /**
     * Identificador único da playlist.
     */
    private int id;
    /**
     * Nome da playlist.
     */
    private String name;
    /**
     * Autor/criador da playlist.
     */
    private String autor;
    /**
     * Lista de músicas da playlist.
     */
    private List<Music> musics;

    /**
     * Construtor que cria uma playlist com nome, autor e lista de músicas.
     * @param name Nome da playlist
     * @param autor Autor/criador da playlist
     * @param musics Lista de músicas da playlist
     */
    public Playlist(String name, String autor, List<Music> musics) {
        this.id = idCounter++;
        this.name = name;
        this.autor = autor;
        this.musics = new ArrayList<>(); // Inicializa a lista
        if (musics != null) { // Verifica se a lista passada não é nula
            for (Music music : musics) {
                this.musics.add(music); 
            }
        }
    }

    /**
     * Construtor para criar uma playlist vazia com nome e autor.
     * @param name Nome da playlist
     * @param autor Autor/criador da playlist
     */
    public Playlist(String name, String autor) {
        this.id = idCounter++;
        this.name = name;
        this.autor = autor;
        this.musics = new ArrayList<>(); // Inicializa a lista
    }

    /**
     * Construtor de cópia.
     * @param p Playlist a ser copiada
     */
    public Playlist(Playlist p) {
        this.id = idCounter++;
        this.id = p.id;
        this.name = p.name;
        this.autor = p.autor;
        this.musics = p.musics; 
    }

    /**
     * Construtor vazio.
     */
    public Playlist() {
        this.id = idCounter++;
        this.name = "";
        this.autor = "";
        this.musics = new ArrayList<>(); // Inicializa a lista
    }

    /**
     * Retorna o identificador único da playlist.
     * @return id da playlist
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador da playlist.
     * @param id Novo id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome da playlist.
     * @return Nome da playlist
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da playlist.
     * @param nome Novo nome
     */
    public void setName(String nome) {
        this.name = nome;
    }

    /**
     * Retorna o autor da playlist.
     * @return Autor da playlist
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Define o autor da playlist.
     * @param autor Novo autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Retorna uma cópia da lista de músicas da playlist.
     * @return Lista de músicas (deep copy)
     */
    public List<Music> getMusics() {
        List<Music> musicsCopy = new ArrayList<>();
        for (Music music : musics) {
            musicsCopy.add(music.clone());
        }
        return musicsCopy; 
    }

    /**
     * Define a lista de músicas da playlist.
     * @param musics Nova lista de músicas
     */
    public void setMusics(List<Music> musics) {
        for (Music musicName : musics) {
            this.musics.add(musicName);
        }
    }

    /**
     * Retorna uma representação textual da playlist.
     * @return String com informações da playlist
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist{");
        sb.append("nome='").append(name).append('\'');
        sb.append(", autor='").append(autor).append('\'');
        sb.append(", musicas=").append(musics);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Compara se duas playlists são iguais.
     * @param o Objeto a comparar
     * @return true se forem iguais, false caso contrário
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;

        Playlist playlist = (Playlist) o;

        if (!name.equals(playlist.name)) return false;
        if (!autor.equals(playlist.autor)) return false;
        return musics.equals(playlist.musics);
    }

    /**
     * Cria uma cópia (clone) da playlist.
     * @return Nova instância de Playlist igual à original
     */
    public Playlist clone() {
        return new Playlist(this);
    }

    /**
     * Retorna o hashCode da playlist (baseado no id).
     * @return hashCode
     */
    public int hashCode() {
        return this.getId();
    }

    /**
     * Adiciona uma música à playlist.
     * @param musicName Música a adicionar
     * @throws AlreadyExistsException Se a música já existir na playlist
     */
    public void addMusic(Music musicName) throws AlreadyExistsException {
        if (this.musics.contains(musicName)) {
            throw new AlreadyExistsException(musicName.getName());
        }
        this.musics.add(musicName);
    }

    /**
     * Retorna a música pelo índice na lista.
     * @param index Índice da música
     * @return Música correspondente
     * @throws IndexOutOfBoundsException Se o índice for inválido
     */
    public Music getMusicBYIndex(int index){
        if (index >= 0 && index < this.musics.size()) {
            return this.musics.get(index);
        }
        else {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista de músicas.");
        }
    }

    /**
     * Remove uma música da playlist.
     * @param musicName Música a remover
     * @return true se a música foi removida, false caso contrário
     */
    public boolean removeMusic(Music musicName){
        return this.musics.remove(musicName);
    }

}
