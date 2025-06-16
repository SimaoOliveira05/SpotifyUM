package org.Model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


import org.Exceptions.AlreadyExistsException;
import org.Exceptions.NotFoundException;
import org.Exceptions.NoPremissionException;
import org.Model.Music.*;
import org.Model.Plan.Plan;
import org.Model.Plan.PlanFree;
import org.Model.Playlist.*;



/**
 * Contém informações pessoais, plano de subscrição, playlists e histórico de reproduções.
 * Permite operações como adicionar/remover playlists, adicionar músicas a playlists,
 * gerir reproduções e alterar dados do utilizador.
 */
public class User implements Serializable{
    /**
     * Nome de utilizador.
     */
    private String username;
    /**
     * Email do utilizador.
     */
    private String email;
    /**
     * Morada do utilizador.
     */
    private String adress;
    /**
     * Password do utilizador.
     */
    private String password;
    /**
     * Plano de subscrição do utilizador.
     */
    private Plan plan;
    /**
     * Lista de playlists do utilizador.
     */
    private List<Playlist> playlists;
    /**
     * Lista de reproduções de música do utilizador.
     */
    private List<MusicReproduction> musicReproductions;

    /**
     * Metodo de construção de um user   
     * @param username nome de utilizador
     * @param email email do utilizador
     * @param adress morada do utilizador
     * @param password password do utilizador
     * @param plan plano do utilizador
     * @param playlists playlists do utilizador
     * @param musicReproductions reproduções de música do utilizador
     */
    public User(String username, String email, String adress, String password, Plan plan, List<Playlist> playlists, List<MusicReproduction> musicReproductions) {   
        this.username = username;
        this.email = email;
        this.adress = adress;
        this.password = password;
        this.plan = plan;
        this.playlists = new ArrayList<>();
        for (Playlist p : playlists) {
            this.playlists.add(p.clone());
        }
        this.musicReproductions = new ArrayList<>();
        for (MusicReproduction m : musicReproductions) {
            this.musicReproductions.add(m);
        }
    }

    /**
     * Construtor vazio da classe User.
     */
    public User() {
        this.username = "";
        this.email = "";
        this.adress = "";
        this.password = "";
        this.plan = new PlanFree();
        this.playlists = new ArrayList<>();
        this.musicReproductions = new ArrayList<>();
    }

    /**
     * Construtor de cópia da classe User.
     * @param u Objeto User a ser copiado.
     */
    public User(User u) {
        this.username = u.username;
        this.email = u.email;
        this.adress = u.adress;
        this.password = u.password;
        this.playlists = u.getPlaylists();
        this.plan = u.plan;
        this.musicReproductions = u.getMusicReproductions();
    }

    /** 
     * Construtor para quando o utilizador se regista
     * @param username nome de utilizador
     * @param email email do utilizador
     * @param adress morada do utilizador
     * @param password password do utilizador
     */
    public User(String username, String email, String adress, String password) {
        this.username = username;
        this.email = email;
        this.adress = adress;
        this.password = password;
        this.plan = new PlanFree();
        this.playlists = new ArrayList<>();
        this.musicReproductions = new ArrayList<>();

    }

    /**
     * Retorna o nome de utilizador.
     * @return Nome de utilizador.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Define o nome de utilizador.
     * @param name Nome a ser definido.
     */
    public void setUsername(String name) {
        this.username = name;
    }

    /**
     * Retorna o email do utilizador.
     * @return Email do utilizador.
     */
    public String getEmail() {
    
        return email;
    }

    /**
     * Define o email do utilizador.
     * @param email Email a ser definido.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna a morada do utilizador.
     * @return Morada do utilizador.
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Define a morada do utilizador.
     * @param morada Morada a ser definida.
     */
    public void setAdress(String morada) {
        this.adress = morada;
    }

    /**
     * Define a password do utilizador.
     * @param password Password a ser definida.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retorna a password do utilizador.
     * @return Password do utilizador.
     */
    public String getPassword() {
        return password;
    }


    /**
     * Define o plano do utilizador.
     * @param plan Plano a ser definido.
     */
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    /**
     * Retorna o plano do utilizador.
     * @return Plano do utilizador.
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * Retorna a lista de playlists do utilizador.
     * @return Lista de playlists.
     */
    public List<Playlist> getPlaylists() {
        List<Playlist> clone = new ArrayList<>();
        for (Playlist p : this.playlists) {
            clone.add(p.clone());
        }
        return clone;
    }

    /**
     * Define a lista de playlists do utilizador.
     * @param playlists Lista de playlists.
     */
    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = new ArrayList<>();
        for (Playlist p : playlists) {
            this.playlists.add(p.clone());
        }
    }

    /**
     * Retorna a lista de reproduções de música do utilizador.
     * @return Lista de reproduções de música.
     */
    public List<MusicReproduction> getMusicReproductions() {
        List<MusicReproduction> clone = new ArrayList<>();
        for (MusicReproduction m : this.musicReproductions) {
            clone.add(m.clone());
        }
        return clone;
    }

    /**
     * Define a lista de reproduções de música do utilizador.
     * @param musicReproductions Lista de reproduções de música.
     */
    public void setMusicReproductions(List<MusicReproduction> musicReproductions) {
        this.musicReproductions = new ArrayList<>();
        for (MusicReproduction m : musicReproductions) {
            this.musicReproductions.add(m.clone());
        }
    }

    @Override
    /**
     * Método toString que retorna uma representação em String do objeto User.
     * @return String com os dados do utilizador.
     */
    public String toString() {
        return 
            "╔═══════════════ DADOS DO UTILIZADOR ═══════════════╗\n" +
            "  👤 Nome de Utilizador: " + username + "\n" +
            "  📧 Email: " + email + "\n" +
            "  🏠 Morada: " + adress + "\n" +
            "  🔑 Password: " + "•".repeat(password.length()) + "\n" +
            "  📋 Plano: " + plan + "\n" +
            "╚═══════════════════════════════════════════════════╝";
    }

    /**
     * Método equals que compara dois objetos User.
     * @param o Objeto a ser comparado.
     * @return true se igual, false caso contrário.
     */


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User that = (User) o;

        if (!username.equals(that.username)) return false;
        if (!email.equals(that.email)) return false;
        if (!adress.equals(that.adress)) return false;
        return password.equals(that.password);
    }

    /**
     * Método clone que cria uma cópia do objeto User.
     * @return Novo objeto User com os mesmos dados.
     */
    public User clone() {
        return new User(this);
    }

    /**
     * Retorna o nome de todas as playlists do user
     * Throws UnsupportedOperationException se for um user free
     * 
     * @return Retorna uma string com os nomes das playlists, uma por linha
     */
    public String namePlaylists() {
        if (this.plan.canAccessLibrary()){
            StringBuilder sb = new StringBuilder();
            for (Playlist p : this.playlists) {
                sb.append(String.format("🎵 [#%03d] %-25s | Autor: %-15s\n", p.getId(), p.getName(), p.getAutor()));            }
            return sb.toString();
        }
        else {
            throw new UnsupportedOperationException("Utilizadores Free não possuem playlists.");
        }
    }

    /**
     * Adiciona uma playlist à Library
     * @param nome -> nome da playlist
    */
    public void addPlaylist(String nome){
        if (this.plan.canAccessLibrary()){
            Playlist p = new Playlist(nome, this.username);
            this.playlists.add(p); 
        }
        else {
            throw new UnsupportedOperationException("Utilizadores Free não podem adicionar playlists.");
        }
    }

    /**
     * Adiciona uma música à lista de reproduções
     * @param m -> música a adicionar
     */
    public void addMusicReproduction(Music m) {
        MusicReproduction mr = new MusicReproduction(m);
        this.musicReproductions.add(mr);
    }

    /**
     * Adiciona uma musica a uma playlist
     * @param playlistId -> id da playlist
     * @param m  -> nome da musica
     * @throws NotFoundException se a playlist não existir
     * @throws AlreadyExistsException se a musica já pertencer a playlist
     * @throws NoPremissionException se for um user free
     */
    public void addMusicPlaylist(int playlistId, Music m) throws NotFoundException, AlreadyExistsException, NoPremissionException {
        for (Playlist p : this.playlists) {
            if (p.getId() == playlistId) {
                if (!p.getAutor().equals(this.username)) {
                    throw new NoPremissionException("Só o autor da playlist pode adicionar músicas.");
                }
                p.addMusic(m);
                return;
            }
        }
        throw new NotFoundException("" + playlistId);
        }

    /**
     * Retorna a playlist através do nome da mesma
     * @param playlistId -> id da playlist
     * @return Uma instancia de uma playlist
     * @throws NotFoundException se a playlist não existir
    */
    public Playlist getPlaylistById(int playlistId) throws NotFoundException {
        if (this.plan.canAccessLibrary()){
            for (Playlist p : this.playlists) {
                if (p.getId() == playlistId) {
                    return p;
                }
            }
            throw new NotFoundException("" + playlistId);
        }
        else {
            throw new UnsupportedOperationException("Utilizadores Free não podem adicionar músicas a playlists.");
        }
    }

    /**
     * Adiciona pontos ao utilizador
     * @throws UnsupportedOperationException se for um user free
     */
    public void addPoints(){
        this.plan.addPoints();
    }

    /** 
     * Remove uma musica de uma playlist
     * @param musicName -> nome da musica
     * @param playlistId -> id da playlist
     * @throws NotFoundException se a playlist não existir
     * @throws NoPremissionException se o utilizador não tiver permissão para aceder à playlist
     * 
     */ 
    public void removeMusicFromPlaylist(Music musicName, int playlistId) throws NotFoundException,NoPremissionException{
        Playlist playlist = null;
        for (Playlist p : this.playlists) {
            if (p.getId() == playlistId) {
                if (!p.getAutor().equals(this.username)) {
                    throw new NoPremissionException("Só o autor da playlist pode remover músicas.");
                }
                playlist = p;
                break;
            }
        }

        if (playlist == null) {
            throw new NotFoundException("" + playlistId);
        }

        if (!playlist.removeMusic(musicName)) {
            throw new NotFoundException(musicName.getName());
        }
    }

    /**
     * Verifica se o utilizador tem acesso à biblioteca
     * @return true se o utilizador tiver acesso à biblioteca, false caso contrário
     */
    public boolean hasLibrary() {
        return this.plan.canAccessLibrary();
    }

    /**
     * Obtém o número de playlists do utilizador
     * @return Número de playlists do utilizador
     */
    public int getUserPlaylistCount(){
        int count = 0;
        for (Playlist p : this.playlists) {
            if (p.getAutor().equals(this.username)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Altera o autor de todas as playlists do utilizador
     * @param newName -> novo nome do autor
     */
    public void changePlaylistAutor(String newName){
        for (Playlist p : this.playlists) {
            if (p.getAutor().equals(this.username)) {
                p.setAutor(newName);
            }
        }
    }

    /**
     * Retorna o número de reproduções de música entre duas datas
     * @param startDate -> data inicial
     * @param endDate -> data final
     * @return -> número de reproduções
     */
    public int getMusicReproductionsCount(LocalDate startDate, LocalDate endDate) {
        return (int) this.musicReproductions.stream()
                .filter(m -> m.getDate().isAfter(startDate) && m.getDate().isBefore(endDate))
                .count();
    }

    /**
     * Adiciona uma playlist à biblioteca do utilizador
     * @param p -> playlist a adicionar
     * @throws AlreadyExistsException se a playlist já existir na biblioteca
     */
    public void addPlaylistToLibrary(Playlist p) throws AlreadyExistsException {
        if(this.playlists.contains(p)){
            throw new AlreadyExistsException(p.getName());
        }
        else{
            this.playlists.add(p);
        }
    }

}
