package org.Utils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Permite abrir um endereço web (URL) no navegador predefinido do sistema operativo, utilizando a API {@link java.awt.Desktop}.
 */
public class BrowserOpener {

    // URL a ser aberto no browser
    private String url;

    /**
     * Construtor da classe BrowserOpener.
     *
     * @param url URL a ser aberto.
     */
    public BrowserOpener(String url) {
        this.url = url;
    }

    /**
     * Tenta abrir o URL fornecido no navegador padrão do sistema.
     *
     * @throws UnsupportedOperationException se o ambiente gráfico (Desktop) não for suportado.
     * @throws URISyntaxException se o URL fornecido for inválido.
     * @throws IOException se ocorrer um erro ao tentar abrir o browser.
     */
    public void abrir() throws UnsupportedOperationException, URISyntaxException, IOException {
        try {
            // Cria o objeto URI a partir do URL fornecido
            URI uri = new URI(url);

            // Verifica se o ambiente Desktop é suportado
            if (Desktop.isDesktopSupported()) {
                // Abre o URI no browser padrão
                Desktop.getDesktop().browse(uri);
            } else {
                throw new UnsupportedOperationException("Desktop não suportado.");
            }
        } catch (URISyntaxException e) {
            // Lança novamente a exceção com a informação do URL
            throw new URISyntaxException(url, url);
        } catch (IOException e) {
            // Lança novamente a exceção original
            throw new IOException("Erro ao tentar abrir o URL: " + url, e);
        }
    }
}
