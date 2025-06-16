package org.View;

import org.Controller.Controller;

/**
 * Classe que representa o menu de perfil do utilizador.
 * Permite aceder e editar dados do perfil e consultar estatísticas.
 */
public class ProfileMenu extends Menu {
    /**
     * Construtor do menu de perfil.
     * @param controller Controlador principal da aplicação.
     * @param menuManager Gestor de menus.
     */
    public ProfileMenu(Controller controller, MenuManager menuManager) {
        super(controller, menuManager);
    }

    /**
     * Mostra o menu de perfil e trata a seleção do utilizador.
     */
    @Override
    public void show() {
        System.out.println("\n👤━━━━━━━━━━━━━━━【 MENU DE PERFIL 】━━━━━━━━━━━━━━━👤\n");
        System.out.printf("%-5s %s\n", "1️⃣", "🔍 Ver Perfil");
        System.out.printf("%-5s %s\n", "2️⃣", "✏️  Editar Perfil");
        System.out.printf("%-5s %s\n", "3️⃣", "🔄 Alterar Plano");
        System.out.printf("%-5s %s\n", "0️⃣", "🔙 Voltar ao Menu Principal");
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
                    System.out.println(getController().getCurrentUser());
                    break;
                case 2:
                    System.out.println("✏️ A redirecionar para o editor de perfil...");
                    getMenuManager().setMenu(new ProfileEditorMenu(getController(), getMenuManager()));
                    break;
                case 3:
                    
                    getMenuManager().setMenu(new PlanMenu(getController(), getMenuManager()));

                    break;
                case 0:
                    System.out.println("🔙 A voltar ao menu principal...");
                    getMenuManager().setMenu(new MainMenu(getController(), getMenuManager()));
                    break;
                default:
                    System.out.println("⚠️ Opção inválida. Tente novamente.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Por favor, insira apenas Números.");
            getScanner().nextLine(); // limpar buffer
        }
    }
}


DELIMITER //
-- DROP TRIGGER tr_VerificarAluguer_BI;
CREATE TRIGGER tr_VerificarAluguer_BI
BEFORE INSERT ON Aluguer
FOR EACH ROW
BEGIN
    -- Verificar validade e operacionalidade da limousine
    IF NOT EXISTS (
        SELECT 1 FROM Limousine
        WHERE Matricula = NEW.LimousineMatricula
          AND operacional = 1
          AND dataVistoria >= NEW.DataHoraFinal
          AND dataSelo >= NEW.DataHoraFinal
          AND dataSeguro  >= NEW.DataHoraFinal
    ) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Erro: limousine nao esta apta ate ao final do aluguer.';
    END IF;

    SET duracaoMinutos = TIMESTAMPDIFF(MINUTE, NEW.DataHoraInicial, NEW.DataHoraFinal);

    IF duracaoMinutos < 60 OR duracaoMinutos > 960 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Erro: a duracao do aluguer deve ser entre 1 hora e 16 horas.';
    END IF;

    -- Verificar sobreposição de alugueres
    IF EXISTS (
        SELECT 1 FROM Aluguer
        WHERE LimousineMatricula = NEW.LimousineMatricula
          AND (
              NEW.DataHoraInicial BETWEEN DataHoraInicial AND DataHoraFinal
              OR NEW.DataHoraFinal BETWEEN DataHoraInicial AND DataHoraFinal
              OR DataHoraInicial BETWEEN NEW.DataHoraInicial AND NEW.DataHoraFinal
              OR DataHoraFinal BETWEEN NEW.DataHoraInicial AND NEW.DataHoraFinal
          )
    ) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Erro: a limousine ja esta reservada nesse periodo!';
    END IF;
END;
//