package entity;

import static java.lang.Math.min;

public class Questionary{
    //uguali per tutti, risparmio di memoria
    private static String[] questions = {
            "What is the name of your favorite pet?",
            "Who is your favorite actor, musician, or artist?",
            "What is your favorite movie?",
            "What is the name of your first school?"
    };

    private String[] answers;

    public Questionary(String[] answers){

        this.answers = answers;
    }

    public Questionary(Questionary q){
        this.answers = q.getAnswers();
        this.questions = q.getQuestions();
    }

    /*  VERIFICA CHE ALMENO n DOMANDE SIANO CORRETTE*/

    public Boolean checkAnswers(Questionary q, int minCorrect){

        String[] tmpAns = q.getAnswers();

        minCorrect = min(minCorrect, tmpAns.length);

        int correct = 0;

        for(int i = 0; i < tmpAns.length; i++){

            if(this.answers[i].equalsIgnoreCase(tmpAns[i])) correct += 1;
        }

        return correct >= minCorrect;
    }

    public Boolean checkAnswers(Questionary q){
        String[] tmpAns = q.getAnswers();
        if(tmpAns.length != this.answers.length) return false;

        int correct = 0;
        for(int i = 0; i < tmpAns.length; i++){

            if(!this.answers[i].equalsIgnoreCase(tmpAns[i])) return false;
        }
        return true;
    }

    public void saveAnswers(Questionary q){

        this.answers = q.getAnswers();
    }

    public Boolean checkAnAnswer(Questionary q, int index){

        return this.answers[index].equals(q.getAnswers()[index]);
    }

    public void saveAnAnswer(Questionary q, int index){

        this.answers[index] = q.getAnswers()[index];
    }

    public String[] getAnswers(){
        return this.answers;
    }

    public String getAnswersList(){
        String out = "";
        for(int i = 0; i < answers.length; i++){
            out += "Risposta " + i + ": " + answers[i] + "\n";
        }
        out += "\n";
        return out;
    }

    public String[] getQuestions(){
        return this.questions;
    }

    @Override
    public boolean equals(Object o){
        try{
            return checkAnswers((Questionary) o);
        }catch(ClassCastException e){
            return false;
        }
    }
}