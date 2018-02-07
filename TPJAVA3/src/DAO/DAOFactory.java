package DAO;

import beans.QuestionBDD;

public class DAOFactory {

    public static DAO<QuestionBDD> getQuestionDAO() {
        return new QuestionDAO();
    }
}
