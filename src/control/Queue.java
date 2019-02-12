package control;

import entity.*;
import exceptions.NickNotQEx;
import exceptions.TCNotExistQEx;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Queue{

    ReadWriteLock lock = new ReentrantReadWriteLock();
    private LinkedList<NodeQueue> users;

    private Queue(){
        this.users = new LinkedList<>();
    }

    /*Variabile per il Singleton*/
    private final static Queue queueSigletonInstance = new Queue();

    public static Queue getQueueSingletonInstance(){
        return queueSigletonInstance;
    }

    public static void main(String[] args){
        /*Crea la coda per il test e i parametri*/
        Queue list = Queue.getQueueSingletonInstance();

        PublicData pubD = new PublicData(new Name("ema"), new Name("alf"), new TaxCode("lfm"), new Nickname("alfy"), new Email("ema@gmail.com"), new GregorianCalendar(97, 7, 31), Gender.MAN);
        PrivateData priD = new PrivateData();

        /*TEST STATEMENT START*/

        /*Test add users*/
        Utente us = new Utente(pubD, priD, new PW("12345"), new Roles(), new Questionary(new String[]{"a", "b", "c", "d"}));
        list.add(us);
        pubD.getNick().set("marta");
        us = new Utente(pubD, priD, new PW("12345"), new Roles(), new Questionary(new String[]{"a", "b", "c", "d"}));
        list.add(us);
        pubD.getNick().set("fil");
        us = new Utente(pubD, priD, new PW("12345"), new Roles(), new Questionary(new String[]{"a", "b", "c", "d"}));
        list.add(us);
        System.out.println(list.toString());

        /*Test search*/
        String[] nickSearch = {"alfy", "marta", "pippo"};
        Utente usfind;
        System.out.println("\t##Start Search Test##");
        for(int i = 0; i < nickSearch.length; i++){

            try{
                usfind = list.find(new Nickname(nickSearch[i]));
                System.out.println("L'utente " + nickSearch[i] + " è stato Trovato");
            }catch(NickNotQEx qEx){
                System.out.println("L'utente " + nickSearch[i] + " non è stato Trovato");
            }
        }
        System.out.println(list.toString());

        /*Test Remove*/
        String[] nickRemove = {"alfy", "fil", "pippo"};

        System.out.println("\t**Start Remove Test**");
        for(int i = 0; i < nickSearch.length; i++){
            list.remove(new Nickname(nickRemove[i]));
        }
        System.out.println(list.toString());


        System.out.println("\n\tTest di singleton: dati pre esistenti");
        Queue list2 = Queue.getQueueSingletonInstance();
        System.out.println(list2.toString());


        System.out.println("\n\tTest di singleton: dopo un add, lista comune a entrambi");
        pubD.getNick().set("giovanni");
        us = new Utente(pubD, priD, new PW("12345"), new Roles(), new Questionary(new String[]{"a", "b", "c", "d"}));
        list2.add(us);
        System.out.println("\tLista 1");
        System.out.println(list.toString());
        System.out.println("\tLista 2");
        System.out.println(list2.toString());
    }

    public void add(Utente us){
        lock.writeLock().lock();
        NodeQueue node = new NodeQueue(us);
        this.users.addLast(node);
        lock.writeLock().unlock();
    }

    public Utente find(Nickname nk) throws NickNotQEx{
        NodeQueue node = searchInQueue(nk);
        if(node == null) throw new NickNotQEx("Nick not found among nodes");
        else return node.getUs();
    }

    public Utente find(TaxCode tc) throws TCNotExistQEx{
        NodeQueue node = searchInQueue(tc);
        if(node == null) throw new TCNotExistQEx("TaxCode not found among nodes");
        else return node.getUs();
    }

    public void remove(Nickname nk) //throws NickNotQEx
    {
        NodeQueue node = searchInQueue(nk);
        if(node != null){
            lock.writeLock().lock();
            node.deleteInfo();
            this.users.remove(node);
            lock.writeLock().unlock();
        }

    }

    private NodeQueue searchInQueue(Nickname nk){
        try {
            lock.readLock().lock();
            String nick = nk.get();
            NodeQueue node;
            for(int i = 0; i < this.users.size(); i++){
                node = this.users.get(i);
                if(node.getNick().get().equals(nick))  //if true found nickname
                {
                    return node;
                }
            }
            return null;
        }finally {
            lock.readLock().unlock();
        }
    }

    private NodeQueue searchInQueue(TaxCode tc){
        try {
            lock.readLock().lock();
            String taxCode = tc.get();
            NodeQueue node;
            for(int i = 0; i < this.users.size(); i++){
                node = this.users.get(i);
                if(node.getTC().get().equals(taxCode))  //if true found nickname
                {
                    return node;
                }
            }
            return null;
        }finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public String toString(){
        NodeQueue node;
        String out;
        out = "#Start List, size=" + this.users.size() + "\n";
        for(int i = 0; i < this.users.size(); i++){
            node = this.users.get(i);
            out += i + ") " + node.toString();
        }
        out += "#End List#\n";
        return out;
    }
}
