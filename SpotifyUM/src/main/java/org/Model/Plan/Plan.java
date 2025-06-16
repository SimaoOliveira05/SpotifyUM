package org.Model.Plan;

import java.io.Serializable;

/**
 *
 * Fornece a estrutura para diferentes tipos de planos, incluindo permissões de acesso
 * e gerenciamento de pontos.
 */
public abstract class Plan implements Serializable {
    /**
     * Número de pontos do plano.
     */
    private int points;
    
    /**
     * Construtor da classe Plan.
     * @return true se o acesso for permitido, false caso contrário.
     */
    public abstract boolean canAccessLibrary();

    /**
     * Retorna se o usuário pode acessar a biblioteca.
     * @return true se for permitido avançar, false caso contrário.
     */
    public abstract boolean canSkip();

    /**
     * Retorna se o usuário pode escolher o que tocar.
     * @return true se for permitido escolher, false caso contrário.
     */
    public abstract boolean canChooseWhatToPlay();

    /**
     * Retorna se o usuário tem acesso aos favoritos.
     * @return true se o acesso aos favoritos for permitido, false caso contrário.
     */
    public abstract boolean hasAccessToFavorites();

    /**
     * Retorna o número de pontos do plano.
     * @return O número de pontos.
     */
    public int getPoints() {
        return points;
    }
    /**
     * Define o número de pontos do plano.
     * @param points O número de pontos a definir.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Adiciona pontos ao usuário.
     */
    public abstract void addPoints();

    /**
     * Retorna uma representação em string do plano.
     * @return String que descreve o plano.
     */
    public abstract String toString();

    /**
     * Retorna o nome do plano.
     * @return O nome do plano.
     */
    public abstract String getPlanName();
}
