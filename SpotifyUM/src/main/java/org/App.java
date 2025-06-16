package org;

import org.Controller.Controller;
import org.View.MenuManager;
import org.Model.SpotifUM;

/**
 * Esta classe contém o método main, que inicializa o modelo, o controlador e o gestor de menus,
 * dando início à execução da aplicação SpotifyUM.
 */
public class App {
    /**
     * Cria as instâncias do modelo, controlador e gestor de menus, e executa o menu principal.
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        // Cria a instância principal do modelo (lógica de negócio)
        SpotifUM spotifUM = new SpotifUM();
        // Cria o controlador que faz a ponte entre o modelo e a interface
        Controller controller = new Controller(spotifUM);
        // Cria o gestor de menus responsável pela interface com o utilizador
        MenuManager menuManager = new MenuManager(controller);
        // Inicia a execução do menu principal
        menuManager.run();
    }
}
