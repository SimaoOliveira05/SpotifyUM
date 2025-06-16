package org.Model.Plan;

/**
 * 
 * Usuários com este plano têm acesso total a todas as funcionalidades e acumulam pontos com base em uma percentagem dos pontos atuais.
 */
public class PlanPremiumTop extends Plan {
    /**
     * Construtor vazio da classe PlanPremiumTop.
     */
    public PlanPremiumTop() {
        this.setPoints(0);
    }

    /**
     * Construtor da classe PlanPremiumTop.
     * @param p O plano de onde copiar os pontos.
     */
    public PlanPremiumTop(Plan p) {
        this.setPoints(p.getPoints() + 100);
    }

    /**
     * Adiciona pontos ao usuário com base em uma percentagem dos pontos atuais.
     */
    public void addPoints() {
        this.setPoints((int) (getPoints() + 0.025 * getPoints()));
    }

    /**
     * Retorna se o usuário pode acessar a biblioteca.
     * @return true, pois usuários Premium Top têm acesso à biblioteca.
     */
    @Override
    public boolean canAccessLibrary() {
        return true;
    }

    /**
     * Retorna se o usuário pode avançar faixas.
     * @return true, pois usuários Premium Top podem avançar faixas.
     */
    public boolean canSkip() {
        return true;
    }

    /**
     * Retorna se o usuário pode escolher o que tocar.
     * @return true, pois usuários Premium Top podem escolher faixas.
     */
    public boolean canChooseWhatToPlay(){
        return true;
    }

    /**
     * Retorna o acesso aos favoritos.
     * @return true, pois usuários Premium Top têm acesso aos favoritos.
     */
    public boolean hasAccessToFavorites() {
        return true;
    }

    /**
     * Retorna uma representação em string do plano PremiumTop.
     * @return String descrevendo o plano e os pontos.
     */
    @Override
    public String toString() {
        return "Plano: PremiumTop\n" +
               "    Pontos: " + getPoints();
    }

    /**
     * Retorna o nome do plano.
     * @return A string "PremiumTop".
     */
    public String getPlanName() {
        return "PremiumTop";
    }
}
