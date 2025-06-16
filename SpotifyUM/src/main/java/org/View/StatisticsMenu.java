package org.View;

import org.Controller.Controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Classe que representa o menu de estatísticas.
 * Permite consultar estatísticas do sistema e do utilizador.
 */
public class StatisticsMenu extends Menu {
    /**
     * Construtor do menu de estatísticas.
     * @param controller Controlador principal da aplicação.
     * @param menuManager Gestor de menus.
     */
    public StatisticsMenu(Controller controller, MenuManager menuManager) {
        super(controller, menuManager);
    }

    /**
     * Mostra o menu de estatísticas e trata a seleção do utilizador.
     */
    @Override
    public void show() {
        System.out.println("\n📊━━━━━━━━━━━━━━━【 MENU DE ESTATÍSTICAS 】━━━━━━━━━━━━━━━📊\n");
        System.out.printf("%-5s %s\n", "1️⃣", "🎵 Música mais reproduzida");
        System.out.printf("%-5s %s\n", "2️⃣", "🎤 Intérprete mais reproduzido");
        System.out.printf("%-5s %s\n", "3️⃣", "🧑‍🎤 Utilizador que ouviu mais músicas");
        System.out.printf("%-5s %s\n", "4️⃣", "🏆 Utilizador com mais pontos");
        System.out.printf("%-5s %s\n", "5️⃣", "🎼 Tipo de música mais reproduzido");
        System.out.printf("%-5s %s\n", "6️⃣", "📚 Número de playlists públicas");
        System.out.printf("%-5s %s\n", "7️⃣", "👑 Utilizador com mais playlists");
        System.out.printf("%-5s %s\n", "0️⃣", "🔙 Voltar ao Menu De Administrador");
        System.out.print("\n👉 Escolhe uma opção: ");
        handleInput();
    }

    /**
     * Lê e processa a opção escolhida pelo utilizador.
     */
    @Override
    public void handleInput() {
        try {
            int choice = getScanner().nextInt();
            getScanner().nextLine(); // limpar buffer
            cleanTerminal();

            switch (choice) {
                case 1:
                    System.out.println(getController().getMostReproducedMusic());
                    break;
                case 2:
                    System.out.println(getController().getMostReproducedArtist());
                    break;
                case 3:
                    getUserWithMostReproductions();
                    break;
                case 4:
                    System.out.println(getController().getUserWithMostPoints());
                    break;
                case 5:
                    System.out.println(getController().getMostReproducedGenre());
                    break;
                case 6:
                    System.out.println(getController().getPublicPlaylistCount());
                    break;
                case 7:
                    System.out.println(getController().getUserWithMostPlaylists());
                    break;
                case 0:
                    getMenuManager().setMenu(new AdminMenu(getController(), getMenuManager()));
                    break;
                default:
                    System.out.println("❌ Opção inválida. Por favor tenta novamente!");
                    break;
            }

        } catch (Exception e) {
            System.out.println("⚠️ Entrada inválida. Por favor insere apenas números.");
            getScanner().nextLine(); 
        }
    }

    /**
     * Método que converte uma string para uma data no formato "dd-MM-yyyy".
     * @param input String a ser convertida.
     * @return Data convertida ou null se a conversão falhar.
     */
    public LocalDate parseDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("❌ Data inválida! Por favor insira uma data no formato correto (DD-MM-YYYY).");
            return null;
        }
    }

    /**
     * Método que permite ao utilizador ver o utilizador com mais reproduções.
     * Pergunta se o utilizador quer ver as reproduções num certo intervalo de tempo.
     * Se sim, pede as datas inicial e final.
     * Se não, mostra o utilizador com mais reproduções sem filtro de data.
     */
    public void getUserWithMostReproductions() {
        System.out.println("Deseja ver as reproduções num certo intervalo de tempo? (s/n)");
        String choice = getScanner().nextLine();
        
        if (choice.equalsIgnoreCase("s")) {
            System.out.print("Insira o intervalo de tempo inicial no formato DD-MM-YYYY: ");
            String startInput = getScanner().nextLine();
            LocalDate startDate = parseDate(startInput);
            
            if (startDate == null) return; // Se a data for inválida, para a função.
    
            System.out.print("Insira o intervalo de tempo final no formato DD-MM-YYYY: ");
            String endInput = getScanner().nextLine();
            LocalDate endDate = parseDate(endInput);
            
            if (endDate == null) return;
    
            System.out.println(getController().getUserWithMostReproductions(startDate, endDate));
            
        } else if (choice.equalsIgnoreCase("n")) {
            System.out.println(getController().getUserWithMostReproductions());
        } else {
            System.out.println("❌ Opção inválida. Por favor tenta novamente!");
        }
    }

    
}
