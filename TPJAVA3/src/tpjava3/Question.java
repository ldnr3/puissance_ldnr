package tpjava3;

import DAO.DAO;
import DAO.DAOFactory;
import DAO.QuestionDAO;
import beans.QuestionBDD;

public class Question extends QGenerator {
    
    private QuestionBDD currentQuestion;
    private DAO<QuestionBDD> myQuestionDAO;

    Question(int niveau) {
        
        this.myQuestionDAO = DAOFactory.getQuestionDAO(); 
        this.setNiveau(niveau);
        this.newQuestion();
    }

    @Override
    public String getQuestion() {
        return this.currentQuestion.getEnonce();
    }

    @Override
    public String getReponse() {
        return this.currentQuestion.getReponse();
    }

    @Override
    public void newQuestion() {
        
        int nRecord = this.myQuestionDAO.compter();               // *** Nombre d'enregistrement de la BDD
        QuestionBDD q;
        
        do {
            int numLigne = 1 + this.getRandomInteger(nRecord);    // *** Numero de la ligne dans la table 1..nRecord
            q = this.myQuestionDAO.getObj(numLigne);
        } while(q.getNiveau() != this.getNiveau());
        
        this.currentQuestion = q;       
    }
}