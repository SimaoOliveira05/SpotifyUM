package org.Utils;
import java.io.Serializable;
import java.io.*;

/**
 * Classe Serialization
 *
 * Utilitário para serialização e desserialização de objetos em Java.
 * Fornece métodos genéricos para exportar (serializar) e importar (desserializar)
 * objetos que implementam a interface {@link Serializable} para/de ficheiros.
 * Não deve ser instanciada.
 *
 * Exemplos de uso:
 * <pre>
 *   MinhaClasse obj = new MinhaClasse();
 *   Serialization.exportar(obj, "objeto.ser");
 *   MinhaClasse objImportado = Serialization.importar("objeto.ser");
 * </pre>
 * 
 */
public class Serialization {
    /**
     * Exporta (serializa) um objeto para um ficheiro.
     *
     * @param objeto O objeto a ser exportado. Deve implementar Serializable.
     * @param caminhoFicheiro Caminho do ficheiro onde o objeto será salvo.
     * @throws IllegalArgumentException Se o objeto for nulo.
     * @throws RuntimeException Se ocorrer um erro de IO durante a exportação.
     */
    public static <T extends Serializable> void exportar (T objeto, String caminhoFicheiro) throws RuntimeException {
        if (objeto == null) {
            throw new IllegalArgumentException("O objeto a ser exportado não pode ser nulo.");
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoFicheiro))) {
            oos.writeObject(objeto);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao exportar o objeto: " + e.getMessage(), e);
        }
    }

    /**
     * Importa (desserializa) um objeto de um ficheiro.
     *
     * @param caminhoFicheiro Caminho do ficheiro de onde o objeto será lido.
     * @param <T> Tipo do objeto esperado, que deve implementar Serializable.
     * @return O objeto importado do ficheiro.
     * @throws RuntimeException Se ocorrer um erro de IO ou de classe durante a importação.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T importar(String caminhoFicheiro) throws RuntimeException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoFicheiro))) {
            Object obj = ois.readObject();
            return (T) obj;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao importar o objeto: " + e.getMessage(), e);
        }
    }
}
