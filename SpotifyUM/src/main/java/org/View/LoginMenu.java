package org.View;

import org.Controller.Controller;

/**
 * Classe que representa o menu de login.
 * Permite autenticação e registo de utilizadores.
 */
public class LoginMenu extends Menu {
    /**
     * Construtor do menu de login.
     * @param controller Controlador principal da aplicação.
     * @param menuManager Gestor de menus.
     */
    public LoginMenu(Controller controller, MenuManager menuManager) {
        super(controller, menuManager);
    }

    /**
     * Mostra o menu de login e trata a seleção do utilizador.
     */
    @Override
    public void show() {
        System.out.println("\n🔐━━━━━━━━━━━━━━━【 MENU DE LOGIN 】━━━━━━━━━━━━━━━🔐\n");
        System.out.printf("%-5s %s\n", "1️⃣", "🔓 Iniciar Sessão");
        System.out.printf("%-5s %s\n", "2️⃣", "🆕 Registar Novo Utilizador");
        System.out.printf("%-5s %s\n", "3️⃣", "❌ Sair");
        System.out.printf("%-5s %s\n", "4️⃣", "👑 Entrar como Administrador");
        System.out.print("\n👉 Escolhe uma opção: ");
        handleInput();
    }
    
    /**
     * Lê e processa a opção escolhida pelo utilizador.
     */
    public void handleInput() {
        try{
            int choice = getScanner().nextInt();
            getScanner().nextLine(); // limpar buffer
            cleanTerminal();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("👋 Até à próxima!");
                    this.setRunning(false);
                    break;
                case 4:
                    getMenuManager().setMenu(new AdminMenu(getController(), getMenuManager()));
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

    /**
     * Realiza o login do utilizador.
     */
    public void login() {
        System.out.print("\n👤 Nome de utilizador: ");
        String nome = getScanner().nextLine();
        System.out.print("🔑 Palavra-passe: ");
        String password = getScanner().nextLine();
        
        boolean loginSuccessful = getController().loginWithMessage(nome, password);
        
        if (loginSuccessful) {
            System.out.println("✅ Login bem-sucedido! Bem-vindo(a), " + nome + "!");
            getMenuManager().setMenu(new MainMenu(getController(), getMenuManager()));
        } else {
            System.out.println("❌ Nome de utilizador ou palavra-passe inválidos.");
        }
    }

    /**
     * Regista um novo utilizador.
     */
    public void register() {
        System.out.println("\n📝 === Registar Novo Utilizador ===");
        System.out.print("👤 Nome de utilizador: ");
        String nome = getScanner().nextLine();
        System.out.print("📧 Email: ");
        String email = getScanner().nextLine();
        System.out.print("🏠 Morada: ");
        String morada = getScanner().nextLine();
        System.out.print("🔑 Palavra-passe: ");
        String password = getScanner().nextLine();
        System.out.println(this.getController().addNewUser(nome, email, morada, password));
    }
}
