/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpjava3;

/**
 *
 * @author stag
 */
public class ArithmeticOperation {
    private int operande1;
    private int operande2;
    private char operateur;
    
    public ArithmeticOperation(int op1, int op2, char operateur) {
        this.operande1 = op1;
        this.operande2 = op2;
        this.operateur = operateur;
    }
    
/**
 * Cette méthode retourne le résultat de l'évaluation de l'opération arithmétique
 * stockée dans l'objet qui l'invoque.
 * Seule les 4 opérations arithmétiques élémentaires sont implémentées.
 * 
 * @return résultat de l'opération "operande1.operateur.operande2"
 */
    
    public int evaluate() {
        int res = 0;
        
        switch(this.operateur) {
            case ArithmeticOperation.ADD :
                res = this.operande1 + this.operande2;
                break;
            case ArithmeticOperation.SUB :
                res = this.operande1 - this.operande2;
                break;
            case ArithmeticOperation.MUL :
                res = this.operande1 * this.operande2;
                break;
            case ArithmeticOperation.DIV :
                res = this.operande1 / this.operande2; // Division entière
                break;
        }
        
        return res;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.operande1);
        sb.append(this.operateur);
        sb.append(this.operande2);
        return sb.toString();
    }
    
    // *** Constantes de classe : operateurs arithmetiques
    public static final char ADD = '+';
    public static final char SUB = '-';
    public static final char MUL = '*';
    public static final char DIV = '/';
    
}
