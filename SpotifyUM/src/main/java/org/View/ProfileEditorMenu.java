package org.View;


import org.Controller.Controller;


/**
 * Classe que representa o menu de edição do perfil do utilizador.
 * Permite alterar dados pessoais do utilizador.
 */
public class ProfileEditorMenu extends Menu {
    /**
     * Construtor do menu de edição de perfil.
     * @param controller Controlador principal da aplicação.
     * @param menuManager Gestor de menus.
     */
    public ProfileEditorMenu(Controller controller, MenuManager menuManager) {
        super(controller, menuManager);
    }

    /**
     * Mostra o menu de edição de perfil e trata a seleção do utilizador.
     */
    @Override
    public void show() {
        System.out.println("\n🎨━━━━━━━━━━━━━━━【 MENU DE EDIÇÃO DE PERFIL 】━━━━━━━━━━━━━━━🎨\n");
        System.out.printf("%-5s %s\n", "1️⃣", "👤 Alterar Nome");
        System.out.printf("%-5s %s\n", "2️⃣", "📧 Alterar Email");
        System.out.printf("%-5s %s\n", "3️⃣", "🔒 Alterar Password");
        System.out.printf("%-5s %s\n", "4️⃣", "💼 Alterar Plano");
        System.out.printf("%-5s %s\n", "0️⃣", "🔙 Voltar ao Menu de Edição de Perfil");
        System.out.print("\n👉 Escolha a sua opção: ");
        handleInput();
    }
    

    /**
     * Lê e processa a opção escolhida pelo utilizador.
     */
    @Override
    public void handleInput() {
        try{
            int choice = getScanner().nextInt();
            getScanner().nextLine();
            cleanTerminal();

            switch (choice) {
                case 1: 
                    System.out.println("✏️ Insira o novo nome:");
                    String newName = getScanner().nextLine();
                    System.out.println(this.getController().changeCurrentUserUserName(newName));
                    break;
                case 2:
                    System.out.println("✏️ Insira o novo email:");
                    String newEmail = getScanner().nextLine();
                    System.out.println(this.getController().changeCurrentUserEmail(newEmail));
                    break;
                case 3:
                    System.out.println("🔐 Insira a sua password atual:");
                    String currentPassword = getScanner().nextLine();

                    if (!getController().isPasswordCorrect(currentPassword)) {
                        System.out.println("❌ Password atual incorreta. Tente novamente.");
                    } else {
                        System.out.println("✏️ Insira a nova password:");
                        String newPassword = getScanner().nextLine();
                        System.out.println(getController().changeCurrentUserPassword(newPassword));
                    break;
                }
                case 4:
                    System.out.println("💼 A abrir menu de planos...");
                    getMenuManager().setMenu(new PlanMenu(getController(), getMenuManager()));
                    break;
                case 0:
                    System.out.println("🔙 A voltar ao menu principal...");
                    getMenuManager().setMenu(new ProfileMenu(getController(), getMenuManager()));
                    break;
                default: 
                    System.out.println("⚠️ Opção inválida. Tente novamente.");
            }
        } catch (Exception e) {
            System.out.println("Por favor, insira apenas Números.");
            getScanner().nextLine(); // limpar buffer
        } 
    }
}
