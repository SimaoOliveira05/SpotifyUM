package org.View;

import org.Controller.*;

/**
 * Classe que representa o menu de seleção de planos.
 * Permite ao utilizador alterar o seu plano de subscrição.
 */
public class PlanMenu extends Menu {
    /**
     * Construtor do menu de planos.
     * @param controller Controlador principal da aplicação.
     * @param menuManager Gestor de menus.
     */
    PlanMenu(Controller controller, MenuManager menuManager) {
        super(controller, menuManager);
    }
    /**
     * Mostra o menu de planos e trata a seleção do utilizador.
     */
    @Override
    public void show() {
        System.out.println("\n💼━━━━━━━━━━━━━━━【 MENU DE PLANOS 】━━━━━━━━━━━━━━━💼\n");
    
        String currentPlan = getController().getCurrentUserPlan();
    
        System.out.printf("%-5s %s%s\n", "1️⃣", "🎧 Plano Free - Acesso limitado, mas grátis!", 
                          currentPlan.equals("Free") ? "  ✅ (Plano Atual)" : "");
        System.out.printf("%-5s %s%s\n", "2️⃣", "💎 Plano PremiumBase - Acesso a criação de playlists!", 
                          currentPlan.equals("PremiumBase") ? "  ✅ (Plano Atual)" : "");
        System.out.printf("%-5s %s%s\n", "3️⃣", "👑 Plano PremiumTop - Tudo incluído + benefícios extra!", 
                          currentPlan.equals("PremiumTop") ? "  ✅ (Plano Atual)" : "");
        System.out.printf("%-5s %s\n", "0️⃣", "🔙 Voltar ao Menu de Perfil");
    
        System.out.print("\n👉 Escolha a sua opção: ");
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
                    System.out.println(getController().setFreePlan());
                    break;
                case 2:
                    System.out.println(getController().setPremiumBasePlan());
                    break;
                case 3:
                    System.out.println(getController().setPremiumTopPlan());
                    break;
                case 0:
                    System.out.println("🔙 A voltar ao Menu de Perfil...");
                    getMenuManager().setMenu(new ProfileMenu(getController(), getMenuManager()));
                    return;
                default:
                    System.out.println("❌ Opção inválida. Por favor, tenta novamente.");
                    return;
            }
        } catch (Exception e) {
            System.out.println("Por favor, insira apenas Números.");
            getScanner().nextLine(); // limpar buffer
        }
    }
}
