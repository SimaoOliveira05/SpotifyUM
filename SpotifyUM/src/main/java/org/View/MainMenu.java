package org.View;
import org.Controller.Controller;

/**
 * Menu principal da aplicação.
 *
 * Permite ao utilizador navegar pelas principais funcionalidades do sistema.
 */
public class MainMenu extends Menu {
    /**
     * Construtor do menu principal.
     * @param controller Controlador principal da aplicação.
     * @param menuManager Gestor de menus.
     */
    public MainMenu(Controller controller, MenuManager menuManager) {
        super(controller, menuManager);
    }

    /**
     * Mostra o menu principal e trata a seleção do utilizador.
     */
    @Override
    public void show() {
        System.out.println("\n🎵✨ Bem-vindo ao SpotifUM! ✨🎵");
        System.out.println("\n🎵━━━━━━━━━━━━━━━【 MENU PRINCIPAL 】━━━━━━━━━━━━━━━🎵\n");
        System.out.printf("%-5s %s\n", "1️⃣", "▶️   Menu de Reprodução");
        System.out.printf("%-5s %s\n", "2️⃣", "👤  Menu de Perfil");
        if (this.getController().currentUserHasLibrary()) {
            System.out.printf("%-5s %s\n", "3️⃣", "🎶  Menu de Playlists");
        }
        System.out.printf("%-5s %s\n", "0️⃣", "🔙  Logout");
        System.out.print("\n👉 Escolhe uma opção: ");
        handleInput();
    }
    
    /**
     * Lê e processa a opção escolhida pelo utilizador.
     */
    public void handleInput() {
        try{
            int choice = getScanner().nextInt();
            getScanner().nextLine();
            cleanTerminal();

            switch (choice) {
                case 1:
                    getMenuManager().setMenu(new ReproductionMenu(getController(), getMenuManager()));
                    break;
                case 2:
                    getMenuManager().setMenu(new ProfileMenu(getController(), getMenuManager()));
                    break;
                case 3:
                    if(this.getController().currentUserHasLibrary())
                        getMenuManager().setMenu(new PlaylistMenu(getController(), getMenuManager()));
                    else
                        System.out.println("\n🚫 Opção inválida. Tenta novamente.");
                    break;
                case 0:
                    System.out.println("\n👋 Logout bem-sucedido. Até à próxima!");
                    getMenuManager().setMenu(new LoginMenu(getController(), getMenuManager()));
                    break;
                default:
                    System.out.println("❌ Opção inválida. Tenta novamente.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Por favor, insira apenas Números.");
            getScanner().nextLine(); // limpar buffer
        }
    }
}
