package tpjava3;


public abstract class QGenerator {
    
    private String question;
    private String reponse;

    public String getQuestion() {
        return question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    
    /**
     * Génére une nouvelle paire question réponse
     * 
     * IN   : niveau int (1|2)    permet de régler le niveau de la question générée
     * 
     * OUT  : les attributs question et réponse contiennent la paire 
     * (question, réponse) générée. La valeur de ces attributs privés
     * est modifiée grâce aux mutateurs (setters) correspondant.
     * 
     * @param niveau
     */
    
    public abstract void newQuestion(int niveau);
    
}
