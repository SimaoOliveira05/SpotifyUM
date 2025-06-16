package org.Model.Plan;

/**
 *
 * Usuários com este plano têm acesso limitado a funcionalidades e acumulam pontos em ritmo básico.
 */
public class PlanFree extends Plan{
    /**
     * Construtor vazio da classe PlanFree.
     */
    public PlanFree() {
        this.setPoints(0);
    }

    /**
     * Construtor da classe PlanFree.
     * @param p O plano de onde copiar os pontos.
     */
    public PlanFree(Plan p){
        this.setPoints(p.getPoints());
    }

    /**
     * Adiciona pontos ao usuário.
     */
    public void addPoints(){
        this.setPoints(getPoints() + 5);
    }

    /**
     * Retorna se o usuário pode acessar a biblioteca.
     * @return false, pois usuários Free não têm acesso à biblioteca.
     */
    public boolean canAccessLibrary() {
        return false;
    }

    /**
     * Retorna se o usuário pode avançar faixas.
     * @return false, pois usuários Free não podem avançar faixas.
     */
    public boolean canSkip() {
        return false;
    }

    /**
     * Retorna se o usuário pode escolher o que tocar.
     * @return false, pois usuários Free não podem escolher faixas.
     */
    public boolean canChooseWhatToPlay(){
        return false;
    }

    /**
     * Retorna o acesso aos favoritos.
     * @return false, pois usuários Free não têm acesso aos favoritos.
     */
    public boolean hasAccessToFavorites() {
        return false;
    }

    /**
     * Retorna uma representação textual do plano Free.
     * @return String descrevendo o plano e os pontos.
     */
    @Override
    public String toString() {
        return "Plano: Free\n" +
               "    Pontos: " + getPoints();
    }
    
    /**
     * Retorna o nome do plano.
     * @return A string "Free".
     */
    public String getPlanName() {
        return "Free";
    }
}
