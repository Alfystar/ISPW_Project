package entity;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public Questionary(String[] answers){

        this.answers = answers;
    }

    public Questionary(Questionary q){
        this.answers = q.getAnswers();
        questions = q.getQuestions();
    }

    /*  VERIFICA CHE ALMENO n DOMANDE SIANO CORRETTE*/

    public Boolean checkAnswers(Questionary q, int minCorrect){

        try{
            lock.readLock().lock();
            String[] tmpAns = q.getAnswers();
            minCorrect = min(minCorrect, tmpAns.length);
            int correct = 0;
            for(int i = 0; i < tmpAns.length; i++){
                if(this.answers[i].equalsIgnoreCase(tmpAns[i])) correct += 1;
            }
            return correct >= minCorrect;
        }finally{
            lock.readLock().unlock();
        }
    }

    public Boolean checkAnswers(Questionary q){
        try{
            lock.readLock().lock();
            String[] tmpAns = q.getAnswers();
            if(tmpAns.length != this.answers.length) return false;

            int correct = 0;
            for(int i = 0; i < tmpAns.length; i++){

                if(!this.answers[i].equalsIgnoreCase(tmpAns[i])) return false;
            }
            return true;
        }finally{
            lock.readLock().unlock();
        }
    }

    public void saveAnswers(Questionary q){
        try{
            lock.writeLock().lock();
            this.answers = q.getAnswers();
        }finally{
            lock.writeLock().unlock();
        }
    }

    public Boolean checkAnAnswer(Questionary q, int index){

        try{
            lock.readLock().lock();
            return this.answers[index].equals(q.getAnswers()[index]);
        }finally{
            lock.readLock().unlock();
        }
    }

    public void saveAnAnswer(Questionary q, int index){

        try{
            lock.readLock().lock();
            this.answers[index] = q.getAnswers()[index];
        }finally{
            lock.readLock().unlock();
        }
    }

    public String[] getAnswers(){
        try{
            lock.readLock().lock();
            return this.answers;
        }finally{
            lock.readLock().unlock();
        }
    }

    public String getAnswersList(){
        try{
            lock.readLock().lock();
            String out = "";
            for(int i = 0; i < answers.length; i++){
                out += "Risposta " + i + ": " + answers[i] + "\n";
            }
            out += "\n";
            return out;
        }finally{
            lock.readLock().unlock();
        }
    }

    public String[] getQuestions(){
        return questions;
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