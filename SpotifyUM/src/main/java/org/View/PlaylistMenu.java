package org.View;

import org.Controller.*;

/**
 * Classe que representa o menu de playlists do utilizador.
 * Permite gerir playlists pessoais e públicas.
 */
public class PlaylistMenu extends Menu {

    /**
     * Construtor do menu de playlists.
     * @param controller Controlador principal da aplicação.
     * @param menuManager Gestor de menus.
     */
    PlaylistMenu(Controller controller, MenuManager menuManager) {
        super(controller, menuManager);
    }

    /**
     * Mostra o menu de playlists e trata a seleção do utilizador.
     */
    @Override
    public void show() {
        System.out.println("\n🎵━━━━━━━━━━━━━━━【 MENU DE PLAYLISTS 】━━━━━━━━━━━━━━━🎵\n");
        System.out.printf("%-5s %s\n", "1️⃣", "🆕 Menu de Criação de Playlist");
        System.out.printf("%-5s %s\n", "2️⃣", "🌍 Tornar Playlist Pública");
        System.out.printf("%-5s %s\n", "3️⃣", "🎶 Adicionar Música à Playlist");
        System.out.printf("%-5s %s\n", "4️⃣", "🗑️  Remover Música de Playlist");
        System.out.printf("%-5s %s\n", "5️⃣", "📂 Ver Minhas Playlists");
        System.out.printf("%-5s %s\n", "6️⃣", "📥 Adicionar Playlist Pública à Biblioteca");
        System.out.printf("%-5s %s\n", "0️⃣", "🔙 Voltar ao Menu Principal");
        System.out.print("\n👉 Escolhe uma opção: ");
        handleInput();
    }

    /**
     * Lê e processa a opção escolhida pelo utilizador.
     */
    public void handleInput() {
        try{
            int choice = getScanner().nextInt();
            getScanner().nextLine(); // Limpar buffer
            cleanTerminal();
            if (this.getController().currentUserHasLibrary()){
                switch (choice) {
                    case 1:
                        getMenuManager().setMenu(new PlaylistCreationMenu(getController(), getMenuManager()));
                        break;
                    case 2:
                        setPlaylistAsPublic();
                        break;
                    case 3:
                        addMusicToPlaylist();
                        break;
                    case 4:
                        removeMusicFromPlaylist();
                        break;
                    case 5:
                        System.out.println(this.getController().listUserPlaylists());
                        break;
                    case 6:
                        addPublicPlaylistToLibrary();
                        break;
                    case 0:
                        getMenuManager().setMenu(new MainMenu(getController(), getMenuManager()));
                        break;
                    default:
                        System.out.println("❌ Opção inválida. Tenta novamente.");
                        break;
                }
            }
            } catch (Exception e) {
                System.out.println("Por favor, insira apenas Números.");
                getScanner().nextLine(); // limpar buffer
        }
    }

    /**
     * Adiciona uma música à playlist do utilizador.
     */
    public void addMusicToPlaylist() {
        System.out.println(this.getController().listUserPlaylists());

        System.out.print("📁 Id da playlist: ");
        int playlistId = getScanner().nextInt();
        getScanner().nextLine();

        System.out.println(this.getController().listAllMusics());
        System.out.print("🎵 Nome da música: ");
        String musicName = getScanner().nextLine();

        System.out.println(this.getController().addMusicToCurrentUserPlaylist(playlistId, musicName));
    }

    /**
     * Remove uma música da playlist do utilizador.
     */
    public void removeMusicFromPlaylist() {
        System.out.println(this.getController().listUserPlaylists());

        System.out.print("📁 Id da playlist: ");
        int playlistId = getScanner().nextInt();
        getScanner().nextLine();

        System.out.println(this.getController().listPlaylistMusics(playlistId));
        System.out.print("🎵 Nome da música: ");    
        String musicName = getScanner().nextLine();

        System.out.println(this.getController().removeMusicFromPlaylist(musicName, playlistId));
    }

    /**
     * Torna uma playlist do utilizador pública.
     */
    public void setPlaylistAsPublic(){
        
        System.out.println(this.getController().listUserPlaylists());

        System.out.print("📁 Id da playlist: ");
        int playlistId = getScanner().nextInt();
        getScanner().nextLine();

        System.out.println(this.getController().setPlaylistAsPublic(playlistId));
    }

    /**
     * Adiciona uma playlist pública à biblioteca do utilizador.
     */
    public void addPublicPlaylistToLibrary() {
        System.out.println(this.getController().listPublicPlaylists());

        System.out.print("📁 Id da playlist pública que pretende adicionar: ");
        int playlistId = getScanner().nextInt();
        getScanner().nextLine();

        System.out.println(this.getController().addPublicPlaylistToLibrary(playlistId));
    }

    /**
     * Cria uma playlist baseada em um género musical.
     */
    public void createGenrePlaylist(){
        System.out.println(this.getController().listUserPlaylists());
        System.out.print("📁 Nome da playlist: ");
        String playlistName = getScanner().nextLine();
        System.out.print("🎵 Qual é o género que deseja?: ");
        String genreName = getScanner().nextLine();
        System.out.println("Duração máxima da playlist (em minutos): ");
        int maxDuration = getScanner().nextInt();
        getScanner().nextLine(); // Limpar buffer
        int duration = maxDuration * 60;
        System.out.println(this.getController().createGenrePlaylist(playlistName, genreName,duration));
    }

}
