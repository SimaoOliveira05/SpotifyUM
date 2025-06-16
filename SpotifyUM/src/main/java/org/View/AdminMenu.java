package org.View;

import org.Controller.Controller;

/**
 *
 * Permite ao administrador importar/exportar a base de dados,
 * adicionar músicas e aceder a estatísticas do sistema.
 */
public class AdminMenu extends Menu {
    /**
     * Construtor do menu de administração.
     * @param controller Controlador principal da aplicação.
     * @param menuManager Gestor de menus.
     */
    public AdminMenu(Controller controller, MenuManager menuManager) {
        super(controller, menuManager);
    }

    /**
     * Mostra o menu de administração e trata a seleção do utilizador.
     */
    @Override
    public void show() {
        System.out.println("\n🎧━━━━━━━━━━━━━━━【 MENU DE ADMINISTRADOR 】━━━━━━━━━━━━━━━🎧\n");
        System.out.printf("%-5s %s\n", "1️⃣", "📂 Importar Base de Dados existente.");
        System.out.printf("%-5s %s\n", "2️⃣", "💾 Exportar Base de Dados.");
        System.out.printf("%-5s %s\n", "3️⃣", "🎼 Adicionar Música ao sistema");
        System.out.printf("%-5s %s\n", "4️⃣", "📊 Mostrar Estatísticas.");
        System.out.printf("%-5s %s\n", "0️⃣", "🔙 Sair para o menu de login");
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

            switch (choice) {
                case 1:
                    importDatabase();
                    break;
                case 2:
                    exportDatabase();
                    break;
                case 3:
                    addNewMusic();
                    break;
                case 4:
                    getMenuManager().setMenu(new StatisticsMenu(getController(), getMenuManager()));
                    break;
                case 0:
                    System.out.println("👋 A sair para o menu de login...");
                    getMenuManager().setMenu(new LoginMenu(getController(), getMenuManager()));
                    break;
                default:
                    System.out.println("❌ Opção inválida. Tenta novamente.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Por favor, insira apenas Números.");
            getScanner().nextLine();
        }
    }

    /**
     * Importa a base de dados a partir de um ficheiro.
     */
    public void importDatabase() {
        System.out.print("📂 Caminho do ficheiro de base de dados a importar: ");
        String filePath = getScanner().nextLine();

        System.out.println(this.getController().importModel(filePath));
    }

    /**
     * Exporta a base de dados para um ficheiro.
     */
    public void exportDatabase() {
        System.out.print("💾 Caminho do ficheiro onde guardar a base de dados: ");
        String filePath = getScanner().nextLine();

        System.out.println(this.getController().exportModel(filePath));
    }

    /**
     * Adiciona uma nova música ao sistema.
     */
    public void addNewMusic(){
        System.out.println("\n🎼================= Adicionar Nova Música =================🎼\n");
        System.out.print("🎵 Nome da nova música: ");
        String musicName = getScanner().nextLine();

        System.out.print("👤 Nome do artista: ");
        String artistName = getScanner().nextLine();

        System.out.print("🏷️  Editora: ");
        String publisher = getScanner().nextLine();

        System.out.print("📝 Letra da música: ");
        String lyrics = getScanner().nextLine();

        System.out.print("🎼 Figuras musicais: ");
        String musicalFigures = getScanner().nextLine();

        System.out.print("🎶 Género musical: ");
        String genre = getScanner().nextLine();

        System.out.print("💿 Nome do álbum: ");
        String albumName = getScanner().nextLine();

        if(!this.getController().albumExists(albumName)){
            System.out.println("⚠️  O álbum não existe.");
            if(askConfirmation("Deseja criar um novo álbum com esse nome?")){
                this.getController().createAlbum(albumName, artistName);
                System.out.println("✅ Álbum criado com sucesso!");
            } else {
                System.out.println("❌ Operação cancelada. Música não adicionada.");
                return;
            }
        }

        System.out.print("⏱️  Duração da música (em segundos): ");
        int duration = getScanner().nextInt();
        getScanner().nextLine(); 

        boolean explicit = askConfirmation("🔞 A sua música contém conteúdo explícito?");

        if(askConfirmation("🌐 A sua música possui um URL associado?")){
            System.out.print("🔗 URL associado: ");
            String url = getScanner().nextLine();
            this.getController().addMusic(musicName, artistName, publisher, lyrics, musicalFigures, genre, albumName, duration, explicit, url);
        }
        else{
            this.getController().addMusic(musicName, artistName, publisher, lyrics, musicalFigures, genre, albumName, duration, explicit, null);
        }
        System.out.println("\n✅ Música adicionada com sucesso!");
    }
}
