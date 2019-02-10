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
    /*Attributi della classe*/
    private LinkedList<NodeQueue> users;

    protected Queue(){
        this.users = new LinkedList<>();
    }

    public static final Queue getQueueSingletonInstance(){
        return LazyCointainer.queueSigletonInstance;
    }

    public static void main(String[] args){
        /*Crea la coda per il test e i parametri*/
        Queue list = Queue.getQueueSingletonInstance();

        PublicData pubD = new PublicData(new Name("ema"), new Name("alf"), new TaxCode("lfm"), new Nickname("alfy"), new Email("ema@gmail.com"), new GregorianCalendar(97, 7, 31), Gender.MAN);
        PrivateData priD = new PrivateData();


        /*TEST STATEMENT START*/

        /*Test add users*/
        Utente us = new Utente(pubD, priD, new PW("12345"), new Roles(), new Questions(new String[]{"a", "b", "c", "d"}));
        list.add(us);
        pubD.getNick().set("marta");
        us = new Utente(pubD, priD, new PW("12345"), new Roles(), new Questions(new String[]{"a", "b", "c", "d"}));
        list.add(us);
        pubD.getNick().set("fil");
        us = new Utente(pubD, priD, new PW("12345"), new Roles(), new Questions(new String[]{"a", "b", "c", "d"}));
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
        us = new Utente(pubD, priD, new PW("12345"), new Roles(), new Questions(new String[]{"a", "b", "c", "d"}));
        list2.add(us);
        System.out.println("\tLista 1");
        System.out.println(list.toString());
        System.out.println("\tLista 2");
        System.out.println(list2.toString());
    }

    /*
    Preso un utente, crea un nodo della lista, di cui tiene traccia
     */
    public void add(Utente us){
        lock.writeLock().lock();
        NodeQueue node = new NodeQueue(us);
        this.users.addLast(node);
        lock.writeLock().unlock();
    }

    /*
    Dato un Nick, lo cerca dentro la lista, se non lo trova solleva un'eccezione
     */
    public Utente find(Nickname nk) throws NickNotQEx{
        try{
            lock.readLock().lock();
            NodeQueue node = searchInQueue(nk);
            if(node == null) throw new NickNotQEx("Nick not found among nodes");
            else return node.getUs();
        }finally{
            lock.readLock().unlock();
        }
    }

    public Utente find(TaxCode tc) throws TCNotExistQEx{
        try{
            lock.readLock().lock();
            NodeQueue node = searchInQueue(tc);
            if(node == null) throw new TCNotExistQEx("TaxCode not found among nodes");
            else return node.getUs();
        }finally{
            lock.readLock().unlock();
        }
    }

    /*
    Dato un nick lo cerca e lo elimina, se non lo trova solleva un'eccezione
     */
    public void remove(Nickname nk) //throws NickNotQEx
    {
        lock.writeLock().lock();
        NodeQueue node = searchInQueue(nk);
        if(node != null){
            node.deleteInfo();
            this.users.remove(node);
        }
        lock.writeLock().unlock();
    }

    private NodeQueue searchInQueue(Nickname nk){
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
    }

    private NodeQueue searchInQueue(TaxCode tc){
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

    /*Variabile per il Singleton*/
    private static class LazyCointainer{
        public final static Queue queueSigletonInstance = new Queue();
    }
}
