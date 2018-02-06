/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author stag
 */
public class QuestionBDD {
    private long id;
    private String enonce;
    private String reponse;
    private int niveau;
    
    public QuestionBDD(long id, String enonce, String reponse, int niveau) {
        this.id = id;
        this.enonce = enonce;
        this.reponse = reponse;
        this.niveau = niveau;
    }

    public long getId() {
        return id;
    }

    public String getEnonce() {
        return enonce;
    }

    public String getReponse() {
        return reponse;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    
    @Override
    public String toString() {
        return "Question { id = " + this.id + ", enonce = " + this.enonce +
                ", reponse = " + this.reponse + ", niveau = " + this.niveau +"}";
    }
}
