package org.Model.Playlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.Exceptions.AlreadyExistsException;
import org.Exceptions.EmptyPlaylistException;
import org.Model.Music.Music;
import org.Model.Music.MusicReproduction;

import java.util.Map;
import java.util.Random;

/**
 * Contém métodos estáticos para gerar playlists baseadas em critérios como género musical,
 * seleção aleatória e músicas favoritas do utilizador.
 * Não deve ser instanciada.
 *
 */
public class PlaylistCreator {
    /**
     * Cria uma playlist de um género musical até uma duração máxima.
     *
     * @param username Nome do utilizador
     * @param name Nome da playlist
     * @param genre Género musical desejado
     * @param maxDuration Duração máxima da playlist (em segundos)
     * @param musics Mapa de músicas disponíveis
     * @param publicPlaylists Mapa de playlists públicas
     * @return Lista de músicas selecionadas
     * @throws AlreadyExistsException Se já existir uma playlist pública com o mesmo nome
     * @throws EmptyPlaylistException Se não for possível criar uma playlist com as restrições
     * @throws IllegalArgumentException Se não existirem músicas do género
     */
    public static List<Music> createGenrePlaylist(String username, String name, String genre, int maxDuration, Map<String,Music> musics,Map<Integer,Playlist> publicPlaylists) throws AlreadyExistsException, EmptyPlaylistException{
        // Verifica se já existe uma playlist pública com este nome

        // Filtra músicas do género pretendido
        List<Music> musicNames = new ArrayList<>();
        for (Music m : musics.values()){
            if (m.getGenre().equals(genre)){
                musicNames.add(m);
            }
        }
        if (musicNames.isEmpty()){
            throw new IllegalArgumentException("Não existem músicas desse género.");
        }

        // Baralha as músicas e seleciona até atingir a duração máxima
        Collections.shuffle(musicNames);
        int totalDuration = 0;
        List<Music> selectedMusics = new ArrayList<>();
        for (Music m : musicNames){
            if (totalDuration + m.getDuration() <= maxDuration){
                selectedMusics.add(m);
                totalDuration += m.getDuration();
            }
        }

        if(selectedMusics.isEmpty()){
            throw new EmptyPlaylistException("Playlist vazia.");
        }

        return selectedMusics;
    }
    

    /**
     * Cria uma playlist aleatória com músicas do sistema.
     *
     * @param musics Mapa de músicas disponíveis
     * @return Lista de músicas selecionadas aleatoriamente
     */
    public static List<Music> createRandomPlaylist(Map<String,Music> musics) {
        Random rand = new Random();
        int numMusics = rand.nextInt(musics.size()) + 1; // Gera um número aleatório entre 1 e o número total de músicas(exclusivo)
        List<Music> musicNames = new ArrayList<>(musics.values());
        List<Music> randomMusics = new ArrayList<>();
        for (int i = 0; i < numMusics; i++) {
            int randomIndex = rand.nextInt(musicNames.size());
            Music randomMusic = musicNames.get(randomIndex);
            randomMusics.add(randomMusic);
            musicNames.remove(randomIndex);
        }
        return randomMusics;
    }   

    /**
     * Cria uma playlist de músicas favoritas do utilizador.
     *
     * @param maxDuration Duração máxima da playlist (em segundos)
     * @param explicit true para incluir apenas músicas explícitas
     * @param reproductions Lista de reproduções do utilizador
     * @param musics Mapa de músicas disponíveis
     * @return Lista de músicas favoritas ordenadas por número de reproduções
     * @throws IllegalArgumentException Se não houver músicas favoritas suficientes
     */
    public static List<Music> createFavoritesPlaylist(int maxDuration, boolean explicit, List<MusicReproduction> reproductions, Map<String,Music> musics) throws IllegalArgumentException{
        // Obter as músicas mais reproduzidas pelo utilizador atual
        List<Music> favoritas = new ArrayList<>();
        Map<String, Integer> contagem = new HashMap<>();
        // Contar reproduções de cada música
        for (MusicReproduction rep : reproductions) {
            if(explicit)
                if(!rep.getMusic().isExplicit()) continue;
            String nome = rep.getMusic().getName();
            contagem.put(nome, contagem.getOrDefault(nome, 0) + 1);
        }
        // Ordenar por mais reproduzidas
        List<String> ordenadas = new ArrayList<>(contagem.keySet());
        ordenadas.sort((a, b) -> contagem.get(b) - contagem.get(a));
        int total = 0;
        for (String nome : ordenadas) {
            Music m = musics.get(nome);
            if (m != null) {
                if (maxDuration > 0 && total + m.getDuration() > maxDuration) break;
                favoritas.add(m);
                total += m.getDuration();
            }
        }
        if (favoritas.isEmpty()) throw new IllegalArgumentException("Não há músicas favoritas suficientes.");
        
        return favoritas;
    }
}
