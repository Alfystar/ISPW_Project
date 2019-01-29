package entity;

import static java.lang.Integer.max;

public class Questions {

    private String[] questions = {
            "What is the name of your favorite pet?",
            "Who is your favorite actor, musician, or artist?",
            "What is your favorite movie?",
            "What is the name of your first school?"
    };

    private String[] answers = {};

    public Questions(String[] answers) {

        this.answers = answers;
    }

    public Questions(Questions q){
        this.answers = q.getAnswers();
        this.questions = q.getQuestions();
    }

    /*  VERIFICA CHE ALMENO n DOMANDE SIANO CORRETTE*/

    public Boolean checkAnswers(Questions q, int n){

        String[] tmpAns = q.getAnswers();

        int minCorrect = max(n, tmpAns.length);

        int correct = 0;

        for(int i = 0; i < tmpAns.length; i++){

            if ( this.answers[i].equals(tmpAns[i]) ) correct += 1;
        }

        return correct >= minCorrect;
    }

    public void saveAnswers(Questions q){

        this.answers = q.getAnswers();
    }

    public Boolean checkAnAnswer(Questions q, int index){

        return this.answers[index].equals(q.getAnswers()[index]);
    }

    public void saveAnAnswer(Questions q, int index){

        this.answers[index] = q.getAnswers()[index];
    }

    public String[] getAnswers(){
        return this.answers;
    }

    public String[] getQuestions(){
        return this.questions;
    }
    //todo: rivedere Class Diagram in UML (aggiunta di un po' di metodi)
}