package org.Model.Plan;

/**
 *
 * Usuários com este plano têm acesso à maioria das funcionalidades e acumulam pontos em ritmo aprimorado.
 */
public class PlanPremiumBase extends Plan {
    /**
     * Construtor vazio da classe PlanPremiumBase.
     */
    public PlanPremiumBase() {
        this.setPoints(0);  
    }

    /**
     * Construtor da classe PlanPremiumBase.
     * @param p O plano de onde copiar os pontos.
     */
    public PlanPremiumBase(Plan p) {
        this.setPoints(p.getPoints());
    }

    /**
     * Adiciona pontos ao usuário.
     */
    public void addPoints() {
        this.setPoints(getPoints() + 10);
    }

    /**
     * Retorna se o usuário pode acessar a biblioteca.
     * @return true, pois usuários Premium Base têm acesso à biblioteca.
     */
    @Override
    public boolean canAccessLibrary() {
        return true;
    }

    /**
     * Retorna se o usuário pode avançar faixas.
     * @return true, pois usuários Premium Base podem avançar faixas.
     */
    public boolean canSkip() {
        return true;
    }

    /**
     * Retorna se o usuário pode escolher o que tocar.
     * @return true, pois usuários Premium Base podem escolher faixas.
     */
    public boolean canChooseWhatToPlay(){
        return true;
    }

    /**
     * Retorna o acesso aos favoritos.
     * @return false, pois usuários Premium Base não têm acesso aos favoritos.
     */
    public boolean hasAccessToFavorites() {
        return false;
    }

    /**
     * Retorna uma representação em String do objeto PlanPremiumBase.
     * @return String descrevendo o plano e os pontos.
     */
    @Override
    public String toString() {
        return "Plano: PremiumBase\n" +
               "    Pontos: " + getPoints();
    }

    /**
     * Retorna o nome do plano.
     * @return A string "PremiumBase".
     */
    public String getPlanName() {
        return "PremiumBase";
    }
}
