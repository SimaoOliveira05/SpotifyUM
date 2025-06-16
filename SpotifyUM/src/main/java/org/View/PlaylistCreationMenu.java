package org.View;

import org.Controller.Controller;

/**
 * Classe que representa o menu de criação de playlists.
 * Permite criar novas playlists para o utilizador.
 */
public class PlaylistCreationMenu extends Menu {

    /**
     * Construtor do menu de criação de playlists.
     * @param controller Controlador principal da aplicação.
     * @param menuManager Gestor de menus.
     */
    public PlaylistCreationMenu(Controller controller, MenuManager menuManager) {
        super(controller, menuManager);
    }

    /**
     * Mostra o menu de criação de playlists e trata a seleção do utilizador.
     */
    @Override
    public void show() {
        System.out.println("\n🎵━━━━━━━━━━━━━━━【 MENU DE CRIAÇÃO DE PLAYLIST 】━━━━━━━━━━━━━━━🎵\n");
        System.out.printf("%-5s %s\n", "1️⃣", "➕📝 Criar Playlist");
        System.out.printf("%-5s %s\n", "2️⃣", "🎼🎚️ Criar Playlist por Género e Duração");
        System.out.printf("%-5s %s\n", "0️⃣", "🔙 Voltar ao Menu de Playlists");
        System.out.print("\n👉 Escolhe uma opção: ");
        handleInput();
    }

    /**
     * Lê e processa a opção escolhida pelo utilizador.
     */
    public void handleInput() {
        try {
            int choice = getScanner().nextInt();
            getScanner().nextLine(); // Limpar buffer
            cleanTerminal();
            switch (choice) {
                case 1:
                    createPlaylist();
                    break;
                case 2:
                    createPlaylistWithGenreAndDuration();
                    break;
                case 0:
                    getMenuManager().setMenu(new PlaylistMenu(getController(), getMenuManager()));
                    break;
                default:
                    System.out.println("❌ Opção inválida. Tenta novamente.\n");
                    break;
            }
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
            getScanner().nextLine(); // Limpar buffer
        }
    }

    /**
     * Cria uma nova playlist com o nome fornecido pelo utilizador.
     */
    public void createPlaylist() {
        System.out.print("📝 Nome da nova playlist: ");
        String playlistName = getScanner().nextLine();
        String result = getController().addToCurrentUserPlaylists(playlistName);
        System.out.println("\n" + result + "\n");
    }

    /**
     * Cria uma nova playlist com um género musical e duração máxima fornecidos pelo utilizador.
     */
    public void createPlaylistWithGenreAndDuration() {
        System.out.print("📝 Nome da nova playlist: ");
        String playlistName = getScanner().nextLine();

        System.out.println(getController().listAllGenres());
        System.out.print("🎵 Género da nova playlist: ");
        String genre = getScanner().nextLine();

        System.out.print("⏳ Duração máxima da nova playlist (em minutos): ");
        int maxDuration = getScanner().nextInt();
        getScanner().nextLine(); // Limpar buffer

        System.out.println(getController().createGenrePlaylist(playlistName, genre, maxDuration*60));
    }
}
