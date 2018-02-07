package tpjava3;


public abstract class QGenerator {
    
    /**
     * La méthode getQuestion retourne l'énoncé de la question sous la forme 
     * d'une chaîne de caractères
     * 
     * @return énoncé de la question
     */
    public abstract String getQuestion();
    
    /**
     * La méthode getResponse retourne la réponse associée à la question sous la 
     * forme d'une châine de caractère
     * 
     * @return réponse à la question posée
     */
    public abstract String getReponse();
    
    /**
     * La méthode newQuestion génére une nouvelle question.
     * 
     * @param niveau int {1|2}   niveau de la question à générer
     */
    
    public abstract void newQuestion(int niveau);
    
    /**
     * génère aléatoirment un entier compris entre 0 et sup-1.
     * 
     * @param sup Borne supérieure des entiers générés aléatoirement
     * 
     * @return un entier compris entre 0 et sup -1
     * 
     */
    
    public int getRandomInteger(int sup) {
        
        int r = (int) Math.floor(sup*Math.random());
        
        return r;
    }
    
}
