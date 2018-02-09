package tpjava3;


public abstract class QGenerator {
    
    private int niveau;     // Niveau de la question : {1|2} 

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
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
     * La méthode newQuestion génére une nouvelle question. Le niveau de la
     * question est contrôlé par l'attribut interne privé du générateur
     * 
     */
    
    public abstract void newQuestion();
    
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
