package org.Controller;

import org.Model.*;
import org.Model.Album.*;
import org.Model.Music.*;
import org.Model.Playlist.*;
import org.Model.User.*;
import org.Model.Plan.*;
import org.Utils.Serialization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.Controller.dtos.*;
import java.time.LocalDate;

/**
 * Controlador principal da aplicação, responsável por gerenciar a lógica de negócios e interagir com o modelo.
 */
public class Controller {
    /**
     * Instância do modelo principal da aplicação.
     */
    private SpotifUM spotifUM;
    
    /**
     * Construtor padrão da classe Controller.
     * Inicializa a instância do modelo SpotifUM.
     */
    public Controller() {
        this.spotifUM = new SpotifUM();
    }

    /**
     * Construtor de cópia da classe Controller.
     * @param controller Instância de Controller a ser copiada.
     */
    public Controller(Controller controller) {
        this.spotifUM = controller.getSpotifUM();
    }

    /**
     * Construtor da classe Controller.
     * @param spotifUM Instância do modelo SpotifUM a ser utilizada.
     */
    public Controller(SpotifUM spotifUM) {
        this.spotifUM = spotifUM;
    }

    /**
     * Retorna a instância do modelo SpotifUM.
     * @return Instância do modelo SpotifUM.
     */
    public SpotifUM getSpotifUM() {
        return spotifUM;
    }

    /**
     * Define a instância do modelo SpotifUM.
     * @param spotifUM Instância do modelo SpotifUM a ser definida.
     */
    public void setSpotifUM(SpotifUM spotifUM) {
        this.spotifUM = new SpotifUM(spotifUM);
    }

    // ===================== AUTENTICAÇÃO E UTILIZADOR =====================

    /**
     * Tenta fazer login e devolve a mensagem de erro se falhar.
     * @param nome Nome do utilizador.
     * @param password Password do utilizador.
     * @return true se o login for bem-sucedido, false caso contrário.
     */
    public boolean loginWithMessage(String nome, String password) {
        try {
            this.getSpotifUM().authenticateUser(nome, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Regista um novo utilizador.
     * @param nome Nome do utilizador.
     * @param email Email do utilizador.
     * @param morada Morada do utilizador.
     * @param password Password do utilizador.
     * @return Mensagem de sucesso ou erro.
     */
    public String addNewUser(String nome, String email, String morada, String password) {
        if (spotifUM.userExists(nome)) {
            return "⚠️ Já existe um utilizador com esse nome. Tenta outro!";
        }
        else {
            spotifUM.addNewUser(nome, email, morada, password);
            return "✅ Utilizador registado com sucesso! Já podes iniciar sessão.";
        }
    }


    //ADMINMENU



    /**
     * Importa um modelo de dados a partir de um ficheiro.
     * @param filePath Caminho do ficheiro a ser importado.
     * @return Mensagem de sucesso ou erro.
     */
    public String importModel(String filePath){
        try {
            this.setSpotifUM(Serialization.importar(filePath));
            return "✅ Base de dados importada com sucesso!";
        } catch (Exception e) {
            return "❌ Erro ao importar a base de dados: " + e.getMessage();
        }
    }

    /**
     * Exporta o modelo de dados para um ficheiro.
     * @param filePath Caminho do ficheiro a ser exportado.
     * @return Mensagem de sucesso ou erro.
     */
    public String exportModel(String filePath){
        try {
            Serialization.exportar(this.getSpotifUM(), filePath);
            return "✅ Base de dados exportada com sucesso!";
        } catch (Exception e) {
            return "❌ Erro ao exportar a base de dados: " + e.getMessage();
        }
    }

    /**
     * Adiciona uma nova música ao modelo.
     * @param musicName Nome da música.
     * @param artistName Nome do artista.
     * @param publisher Nome do editor.
     * @param lyrics Letra da música.
     * @param musicalFigures Figuras musicais.
     * @param genre Género da música.
     * @param albumName Nome do álbum.
     * @param duration Duração da música em segundos.
     * @param explicit Se a música é explícita ou não.
     * @param url URL da música (opcional).
     * @return Mensagem de sucesso ou erro.
     */
    public String addMusic(String musicName, String artistName, String publisher, String lyrics, String musicalFigures, String genre, String albumName, int duration, boolean explicit, String url){
        try {
            this.getSpotifUM().addNewMusic(musicName, artistName, publisher, lyrics, musicalFigures, genre, albumName, duration, explicit, url);
            return "✅ Música \"" + musicName + "\" adicionada com sucesso!";
        } catch (Exception e) {
            return "❌ Erro ao adicionar a música: " + e.getMessage();
        }
    }

    /**
     * Adiciona um novo álbum ao modelo.
     * @param albumName Nome do álbum.
     * @param artistName Nome do artista.
     * @return Mensagem de sucesso ou erro.
     */
    public String createAlbum(String albumName, String artistName){
        try {
            this.getSpotifUM().addNewAlbum(albumName, artistName);
            return "✅ Álbum \"" + albumName + "\" criado com sucesso!";
        } catch (Exception e) {
            return "❌ Erro ao criar o álbum: " + e.getMessage();
        }
    }

    /**
     * Verifica se um álbum existe no modelo.
     * @param albumName Nome do álbum.
     * @return true se o álbum existir, false caso contrário.
     */
    public boolean albumExists(String albumName){
        return this.getSpotifUM().albumExists(albumName);
    }


    //MAINMENU

    /**
     * Verifica se o utilizador atual tem uma biblioteca.
     * @return true se o utilizador tiver uma biblioteca, false caso contrário.
     */
    public boolean currentUserHasLibrary() {
        User user = this.getSpotifUM().getCurrentUser();
        return user != null && user.hasLibrary();
    }

    /**
     * Verifica se o utilizador atual tem uma playlist.
     * @param newName Novo nome do utilizador.
     * @return true se o utilizador tiver uma playlist, false caso contrário.
     */
    public String changeCurrentUserUserName(String newName){
        try{
            this.getSpotifUM().changeCurrentUserName(newName);
        } catch (Exception e){
            return "❌ Erro ao alterar o nome de utilizador: " + e.getMessage();
        }
        return "✅ Nome de utilizador alterado com sucesso!";
    }

    /**
     * Altera o email do utilizador atual.
     * @param newEmail Novo email do utilizador.
     * @return Mensagem de sucesso ou erro.
     */
    public String changeCurrentUserEmail(String newEmail){
        this.getSpotifUM().setCurrentUserEmail(newEmail);
        return "✅ Email alterado com sucesso!";
    }

    /**
     * Verifica se a password do utilizador atual está correta.
     * @param password Password a ser verificada.
     * @return true se a password estiver correta, false caso contrário.
     */
    public boolean isPasswordCorrect(String password) {
        return this.getSpotifUM().isPasswordCorrect(password);
    }
    
    /**
     * Altera a password do utilizador atual.
     * @param newPassword Nova password do utilizador.
     * @return Mensagem de sucesso ou erro.
     */
    public String changeCurrentUserPassword(String newPassword) {
        this.getSpotifUM().setCurrentUserPassword(newPassword);
        return "✅ Password alterada com sucesso!";
    }

    /**
     * Obtém informações do utilizador atual.
     * @return String com as informações do utilizador.
     */
    public String getCurrentUser() {
        return "📄 Informações do Perfil:\n" + this.getSpotifUM().getCurrentUser().toString();
    }

    /**
     * Define o plano do utilizador atual como Free.
     * @return Mensagem de sucesso.
     */
    public String setFreePlan(){
        this.getSpotifUM().getCurrentUser().setPlan(new PlanFree(this.getSpotifUM().getCurrentUser().getPlan()));
        return "✅ Plano Free selecionado. Desfrute da música com anúncios!";
    }

    /**
     * Define o plano do utilizador atual como PremiumBase.
     * @return Mensagem de sucesso.
     */
    public String setPremiumBasePlan(){
        this.getSpotifUM().getCurrentUser().setPlan(new PlanPremiumBase(this.getSpotifUM().getCurrentUser().getPlan()));
        return "✨ Plano PremiumBase ativado. Boa escolha!";
    }

    /**
     * Define o plano do utilizador atual como PremiumTop.
     * @return Mensagem de sucesso.
     */
    public String setPremiumTopPlan(){
        this.getSpotifUM().getCurrentUser().setPlan(new PlanPremiumTop(this.getSpotifUM().getCurrentUser().getPlan()));
        return "👑 Bem-vindo ao topo! Plano PremiumTop ativado com sucesso.";
    }

    /**
     * Adiciona uma playlist à lista de playlists do utilizador atual.
     * @param playlistName Nome da playlist a ser adicionada.
     * @return Mensagem de sucesso ou erro.
     */
    public String addToCurrentUserPlaylists(String playlistName){
        try{
            this.getSpotifUM().addToCurrentUserPlaylist(playlistName);
        } catch (Exception e){
            return "❌ Erro ao adicionar a playlist: " + e.getMessage();
        }
        return "✅ Playlist \"" + playlistName + "\" adicionada à lista de playlists do utilizador.";
    }

    /**
     * Adiciona uma música à playlist do utilizador atual.
     * @param playlistId ID da playlist.
     * @param musicName Nome da música a ser adicionada.
     * @return Mensagem de sucesso ou erro.
     */
    public String addMusicToCurrentUserPlaylist(int playlistId,String musicName){
        try{
            this.getSpotifUM().addMusicToCurrentUserPlaylist(playlistId, musicName);
        } catch (Exception e){
            return "❌ Erro ao adicionar a música à playlist: " + e.getMessage();
        }
        return "✅ Música \"" + musicName + "\" adicionada à playlist \"" + this.getSpotifUM().getUserPlaylistById(playlistId).getName() + "\".";
    }
    
    /**
     * Lista as playlists do utilizador atual.
     * @return String com as playlists do utilizador.
     */
    public String listUserPlaylists(){
        try {
            String playlists = this.getSpotifUM().listCurrentUserPlaylists();
            if (playlists.isEmpty()) {
                return "📭 Não existem playlists.";
            } else {
                return "📚 As tuas playlists:\n" + playlists;
            }
        } catch (Exception e) {
            return "❌ Erro ao listar playlists: " + e.getMessage();
        }
    }

    /**
     * Lista as playlists públicas.
     * @return String com as playlists públicas.
     */
    public String listPublicPlaylists(){
        try {
            String playlists = getSpotifUM().listPublicPlaylists();
            if (playlists.isEmpty()) {
                return "📭 Não existem playlists públicas.";
            } else {
                return "📚 Playlists Públicas:\n" + playlists;
            }
        } catch (Exception e) {
            return "❌ Erro ao listar playlists: " + e.getMessage();
        }
    }

    /**
     * Lista todas as músicas disponíveis.
     * @return String com as músicas disponíveis.
     */
    public String listAllMusics() {
        try {
            String musics = this.getSpotifUM().listAllMusics();
            if (musics.isEmpty()) {
                return "📭 Não existem músicas disponíveis.";
            } else {
                return "🎵 Lista de músicas disponíveis:\n\n" + musics;
            }
        } catch (Exception e) {
            return "❌ Erro ao listar músicas: " + e.getMessage();
        }
    }

    /**
     * Lista todas as músicas de uma playlist específica.
     * @param playlistId ID da playlist.
     * @return String com as músicas da playlist.
     */
    public String listPlaylistMusics(int playlistId) {
        try {
            String musics = this.getSpotifUM().listAllMusicsInPlaylist(playlistId);
            if (musics.isEmpty()) {
                return "";
            } else {
                return "🎵 Lista de músicas na playlist:\n\n" + musics;
            }
        } catch (Exception e) {
            return "❌ Erro ao listar músicas da playlist: " + e.getMessage();
        }
    }

    /**
     * Lista todos os álbuns disponíveis.
     * @return String com os álbuns disponíveis.
     */
    public String listAllAlbums() {
        try {
            String albums = this.getSpotifUM().listAllAlbums();
            if (albums.isEmpty()) {
                return "📭 Não existem álbuns disponíveis.";
            } else {
                return "🖼️  Lista de álbuns disponíveis:\n\n" + albums;
            }
        } catch (Exception e) {
            return "❌ Erro ao listar álbuns: " + e.getMessage();
        }
    }

    /**
     * Lista todos os géneros disponíveis.
     * @return String com os géneros disponíveis.
     */
    public String listAllGenres(){
        try {
            String genres = this.getSpotifUM().listAllGenres();
            if (genres.isEmpty()) {
                return "📭 Não existem géneros disponíveis.";
            } else {
                return "🎶 Lista de géneros disponíveis:\n\n" + genres;
            }
        } catch (Exception e) {
            return "❌ Erro ao listar géneros: " + e.getMessage();
        }
    }

    /**
     * Adiciona uma playlist pública à biblioteca do utilizador atual.
     * @param playlistId ID da playlist a ser adicionada.
     * @return Mensagem de sucesso ou erro.
     */
    public String addPublicPlaylistToLibrary(int playlistId){
        try{
            this.getSpotifUM().addPlaylistToCurrentUserLibrary(playlistId);;
        } catch (Exception e){
            return "❌ Erro ao adicionar a playlist à biblioteca: " + e.getMessage();
        }
        return "✅ Playlist \"" + this.getSpotifUM().getPublicPlaylistById(playlistId).getName() + "\" adicionada à biblioteca do utilizador.";
    }

    /**
     * Remove uma música de uma playlist do utilizador atual.
     * @param musicName Nome da música a ser removida.
     * @param playlistId ID da playlist.
     * @return Mensagem de sucesso ou erro.
     */
    public String removeMusicFromPlaylist(String musicName, int playlistId){
        try{
            this.getSpotifUM().removeMusicFromPlaylist(musicName, playlistId);
        } catch (Exception e){
            return "❌ Erro ao remover a música da playlist: " + e.getMessage();
        }
        return "✅ Música \"" + musicName + "\" removida da playlist \"" + this.getSpotifUM().getUserPlaylistById(playlistId).getName() + "\".";
    }

    /**
     * Define uma playlist como pública.
     * @param playlistId ID da playlist a ser definida como pública.
     * @return Mensagem de sucesso ou erro.
     */
    public String setPlaylistAsPublic(int playlistId){
        try {
            this.getSpotifUM().setPlaylistAsPublic(playlistId);
            return "✅ Playlist \"" + this.getSpotifUM().getUserPlaylistById(playlistId).getName() + "\" definida como pública.";
        } catch (Exception e) {
            return "❌ Erro ao definir a playlist como pública: " + e.getMessage();
        }
    }

    /**
     * Obtém o nome das músicas de uma playlist específica.
     * @param playlistId ID da playlist.
     * @return Lista de nomes das músicas da playlist.
     */
    public Optional<List<String>> getPlaylistMusicNames(int playlistId){
        try{
            Playlist p = spotifUM.getCurrentUser().getPlaylistById(playlistId);
            List<String> musicNames = new ArrayList<>();
            for(Music m: p.getMusics()){
                musicNames.add(m.getName());
            }
            return Optional.of(musicNames);

        } catch(Exception e){
            return Optional.empty();
        }
    }

    /**
     * Obtém o nome de uma playlist específica.
     * @param playlistId ID da playlist.
     * @return Nome da playlist.
     */
    public String getPlaylistId(int playlistId){
        try{
            Playlist p = spotifUM.getCurrentUser().getPlaylistById(playlistId);
            return p.getName();
        } catch(Exception e){
            return "❌ Erro ao obter o nome da playlist: " + e.getMessage();
        }
    }


    /**
     * Obtém o nome das músicas de um álbum específico.
     * @param albumName Nome do álbum.
     * @return Lista de nomes das músicas do álbum.
     */
    public Optional<List<String>> getAlbumMusicNames(String albumName){
        try{
            Album a = spotifUM.getAlbumByName(albumName);
            List<String> musicNames = new ArrayList<>();
            for(Music m: a.getMusics()){
                musicNames.add(m.getName());
            }
            return Optional.of(musicNames);

        } catch(Exception e){
            return Optional.empty();
        }
    }

    /**
     * Obtém o nome das músicas de uma playlist de favoritos.
     * @param explicit Se a música é explícita ou não.
     * @param maxduration Duração máxima da música.
     * @return Lista de nomes das músicas da playlist de favoritos.
     */
    public Optional<List<String>> getFavoritePlaylistMusicNames(boolean explicit, int maxduration){
        try{
            PlaylistFavorites p = this.getSpotifUM().createFavoritesPlaylist(maxduration, explicit);
            List<Music> musics = p.getMusics();
            List<String> musicNames = new ArrayList<>();
            for(Music m: musics){
                musicNames.add(m.getName());
            }
            return Optional.of(musicNames);
        } catch(Exception e){
            return Optional.empty();
        }
    }

    /**
     * Cria uma playlist de gênero.
     * @param playlistName Nome da playlist.
     * @param genre Gênero da música.
     * @param maxDuration Duração máxima da playlist.
     * @return Mensagem de sucesso ou erro.
     */
    public String createGenrePlaylist(String playlistName, String genre, int maxDuration){
        try {
            this.getSpotifUM().createGenrePlaylist(playlistName, genre, maxDuration);
            return "✅ Playlist \"" + playlistName + "\" criada com sucesso!";
        } catch (Exception e) {
            return "❌ Erro ao criar a playlist: " + e.getMessage();
        }
    }

    /**
     * Toca uma música.
     * @param musicName Nome da música a ser tocada.
     * @return Informações da música tocada.
     */
    public MusicInfo playMusic(String musicName){
        try{
            getSpotifUM().addPointsToCurrentUser();
            Music music = getSpotifUM().getMusicByName(musicName);
            getSpotifUM().incrementArtistReproductions(music.getInterpreter());
            getSpotifUM().incrementGenreReproductions(music.getGenre());

            String url = "";
            if(music.getClass() == MusicMultimedia.class){
                url = ((MusicMultimedia) music).getUrl();
            }
            


            MusicInfo mf = new MusicInfo(music.getName(), music.getInterpreter(), music.getLyrics(), url, music.isExplicit());


            getSpotifUM().playMusic(musicName);
            getSpotifUM().addToCurrentUserMusicReproductions(musicName);;



            return mf;
        }catch (Exception e){
            MusicInfo mf = new MusicInfo(e.getMessage());
            return mf;
        }
    }

    /**
     * Obtém o nome das músicas de uma playlist aleatória.
     * @return Lista de nomes das músicas da playlist aleatória.
     */
    public Optional<List<String>> getRandomPlaylistMusicNames(){
        try{
            PlaylistRandom p = this.getSpotifUM().getRandomPlaylist();
            List<String> musicNames = new ArrayList<>();
            for(Music m: p.getMusics()){
                musicNames.add(m.getName());
            }
            return Optional.of(musicNames);
        } catch(Exception e){
            return Optional.empty();
        }
    }

    /**
     * Verifica se uma música existe no modelo.
     * @param musicName Nome da música.
     * @return true se a música existir, false caso contrário.
     */
    public boolean musicExists(String musicName){
        return this.spotifUM.musicExists(musicName);
    }


    /**
     * Verifica se o utilizador atual pode pular músicas.
     * @return true se o utilizador puder pular, false caso contrário.
     */
    public boolean canCurrentUserSkip(){
        return this.spotifUM.canCurrentUserSkip();
    }

    /**
     * Verifica se o utilizador atual pode escolher o que tocar.
     * @return true se o utilizador puder escolher, false caso contrário.
     */
    public boolean canCurrentUserChooseWhatToPlay(){
        return this.spotifUM.canCurrentUserChooseWhatToPlay();
    }

    /**
     * Verifica se o utilizador atual tem acesso às músicas favoritas.
     * @return true se o utilizador tiver acesso, false caso contrário.
     */

    public boolean currentUserAccessToFavorites(){
        return this.spotifUM.currentUserAccessToFavorites();
    }

    /**
     * Obtém a música mais reproduzida.
     * @return Mensagem com a música mais reproduzida.
     */
    public String getMostReproducedMusic(){
        try {
            Music music = this.spotifUM.mostReproducedMusic();
            return "🎵 Música mais reproduzida: " + music.getName() + " - " + music.getInterpreter();
        } catch (Exception e) {
            return "❌ Erro ao obter a música mais reproduzida: " + e.getMessage();
        }
    }

    /**
     * Obtém o artista mais reproduzido.
     * @return Mensagem com o artista mais reproduzido.
     */
    public String getMostReproducedArtist(){
        try {
            String artist = this.spotifUM.getTopArtistName();
            return "🎤 Artista mais reproduzido: " + artist;
        } catch (Exception e) {
            return "❌ Erro ao obter o artista mais reproduzido: " + e.getMessage();
        }
    }

    /**
     * Obtém o utilizador com mais pontos.
     * @return Mensagem com o utilizador com mais pontos.
     */
    public String getUserWithMostPoints(){
        try {
            User user = this.spotifUM.getUserWithMostPoints();
            return "🏆 Utilizador com mais pontos: " + user.getUsername() + " com " + user.getPlan().getPoints() + " pontos.";
        } catch (Exception e) {
            return "❌ Erro ao obter o utilizador com mais pontos: " + e.getMessage();
        }
    }

    /**
     * Obtém o gênero mais reproduzido.
     * @return Mensagem com o gênero mais reproduzido.
     */
    public String getMostReproducedGenre(){
        try {
            String genre = this.spotifUM.getGenreWithMostReproductions();
            return "🎶 Género mais reproduzido: " + genre;
        } catch (Exception e) {
            return "❌ Erro ao obter o género mais reproduzido: " + e.getMessage();
        }
    }

    /**
     * Obtém o plano atual do utilizador.
     * @return Mensagem com o plano atual do utilizador.
     */
    public String getCurrentUserPlan(){
        return this.spotifUM.getCurrentUserPlanName();
    }

    /**
     * Obtém o utilizador com mais playlists.
     * @return Mensagem com o utilizador com mais playlists.
    */
    public String getUserWithMostPlaylists(){
        try {
            User user = this.spotifUM.getUserWithMostPlaylists();
            if (user.getPlaylists().isEmpty()) {
                return "Não existe utilizadores com playlists.";
            }
            return "📚 Utilizador com mais playlists: " + user.getUsername() + " com " + user.getUserPlaylistCount() + " playlists.";
        } catch (Exception e) {
            return "❌ Erro ao obter o utilizador com mais playlists: " + e.getMessage();
        }
    }

    /**
     * Obtém o utilizador que mais reproduziu músicas.
     * @return Mensagem com o utilizador que mais reproduziu músicas.
     */
    public String getUserWithMostReproductions(){
        try {
            User user = this.spotifUM.getUserWithMostReproductions();
            if (user.getMusicReproductions().isEmpty()) {
                return "Não existe utilizadores com reproduções.";
            }
            return "👤 Utilizador que ouviu mais músicas: " + user.getUsername() + " com " + user.getMusicReproductions().size() + " reproduções.";
        } catch (Exception e) {
            return "❌ Erro ao obter o utilizador que ouviu mais músicas: " + e.getMessage();
        }
    }

    /**
     * Obtém o utilizador que mais reproduziu músicas entre duas datas.
     * @param startDate Data de início.
     * @param endDate Data de fim.
     * @return Mensagem com o utilizador que mais reproduziu músicas entre as datas especificadas.
     */
    public String getUserWithMostReproductions(LocalDate startDate, LocalDate endDate){
        try {
            User user = this.spotifUM.getUserWithMostReproductions(startDate, endDate);
            if (user.getMusicReproductions().isEmpty()) {
                return "Não existe utilizadores com reproduções.";
            }
            return "👤 Utilizador que ouviu mais músicas entre " + startDate + " e " + endDate + ": " + user.getUsername() + " com " + user.getMusicReproductionsCount(startDate, endDate) + " reproduções.";
        } catch (Exception e) {
            return "❌ Erro ao obter o utilizador que ouviu mais músicas: " + e.getMessage();
        }
    }


    /**
     * Obtém o número de playlists públicas.
     * @return String com o número de playlists públicas ou mensagem de erro
     */
    public String getPublicPlaylistCount(){
        try {
            int count = this.spotifUM.getPublicPlaylistSize();
            return "📊 Número de playlists públicas: " + count;
        } catch (Exception e) {
            return "❌ Erro ao obter o número de playlists públicas: " + e.getMessage();
        }
    }
}

