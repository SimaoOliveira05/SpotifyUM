package org.View;
import org.Controller.Controller;

/**
 * Classe que gere a navegação entre menus.
 */
public class MenuManager {
    /** Menu atual apresentado ao utilizador. */
    private Menu currentMenu; // O menu atual em exibição

    /**
     * Construtor do gestor de menus.
     * @param controller Controlador principal da aplicação.
     */
    public MenuManager(Controller controller) {
        this.currentMenu = new LoginMenu(controller, this); // Inicia com o menu de login
    }

    /**
     * Define o menu atual.
     * @param menu Menu a apresentar.
     */
    public void setMenu(Menu menu) {
        this.currentMenu = menu;
    }

    /**
     * Obtém o menu atual.
     * @return Menu atual.
     */
    public Menu getCurrentMenu() {
        return currentMenu;
    }

    /**
     * Inicia o ciclo principal de apresentação dos menus.
     */
    public void run() {
        while (currentMenu.isRunning()) {
            currentMenu.show();
        }
        this.currentMenu.closeScanner();
    }
}


