package tpjava3;

public class Calcul extends QGenerator {
    
    private ArithmeticOperation op;

    public Calcul(int niveau) {
        this.newQuestion(niveau);
    }

    @Override
    public void newQuestion(int niveau) {
        int operande1 = 0;
        int operande2 = 0;
        char operateur = ArithmeticOperation.ADD;
        
        switch(niveau) {
            case 2 :
                operande1 = this.getRandomInteger(1000);  // Entier  0..999
                operande2 = this.getRandomInteger(1000);  // Entier  0..999
                switch(this.getRandomInteger(3)) {
                    case 2 :
                        operateur = ArithmeticOperation.MUL;
                        break;
                    case 1 :
                        operateur = ArithmeticOperation.SUB;
                        break;
                    default :
                        operateur = ArithmeticOperation.ADD;
                }
                break;
                
            default :
                operande1 = this.getRandomInteger(10);  // Entier  0..9
                operande2 = this.getRandomInteger(10);  // Entier  0..9
                switch(this.getRandomInteger(2)) {
                    case 1 :
                        operateur = ArithmeticOperation.SUB;
                        break;
                    default :
                        operateur = ArithmeticOperation.ADD;
                }        
        }
        
        this.op = new ArithmeticOperation(operande1,operande2,operateur);
    }

    @Override
    public String getQuestion() {
        return this.op.toString();
    }

    @Override
    public String getReponse() {
        return Integer.toString(this.op.evaluate());
    }  
}
