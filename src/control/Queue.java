package control;
import entity.*;

import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Queue {
    private LinkedList<NodeQueue> users;

    private static class LazyCointainer{
        public final static Queue queueSigletonInstance = new Queue();
    }

    static Queue queue = new Queue();

    protected Queue()
    {
        this.users = new LinkedList<>();
    }

    public static final Queue getQueueSingletonInstance() {
        return LazyCointainer.queueSigletonInstance;
    }

    /*
    Preso un utente, crea un nodo della lista, di cui tiene traccia
     */
    public void add(Utente us)
    {
        NodeQueue node= new NodeQueue(us);
        this.users.addLast(node);
    }

    /*
    Dato un Nick, lo cerca dentro la lista, se non lo trova ritorna null
     */
    public Utente find(Nickname nk)
    {
        NodeQueue node = searchInQueue(nk);
        if(node!=null) return node.getUs();
        else return null;
    }

    /*
    Dato un nick lo cerca e lo elimina, se non lo trova solleva un eccezione
     */
    //todo: aggiungere eccezione di fallimento se il nick non trova
    public void remove(Nickname nk)
    {
        NodeQueue node = searchInQueue(nk);
        if(node!=null)
        {
            node.deleteInfo();
            this.users.remove(node);
        }
    }

    private NodeQueue searchInQueue(Nickname nk)
    {
        String nick = nk.get();
        NodeQueue node;
        for (int i = 0; i < this.users.size(); i++) {
            node=this.users.get(i);
            if(node.getNick().get().equals(nick))  //if true found nickname
            {
                return node;
            }
        }
        return null;
    }

    @Override
    public String toString ()
    {
        NodeQueue node;
        String out;
        out="#Start List, size=" + this.users.size()+"\n";
        for (int i = 0; i < this.users.size(); i++) {
            node=this.users.get(i);
            out+= i+") "+node.toString();
        }
        out+="#End List#\n";
        return out;
    }

    public static void main(String Argv[])
    {
        /*Crea la coda per il test e i parametri*/
        Queue list = new Queue();

        PublicData pubD = new PublicData(new Name("ema"), new Name("alf"), new TaxCode("lfm"), new Nickname("alfy") ,new Email("ema@gmail.com"), new GregorianCalendar(97,7,31), Gender.MAN);
        PrivateData priD = new PrivateData();



        /*TEST STATEMENT START*/

        /*Test add users*/
        Utente us = new Utente(pubD,priD,new PW("12345"),new Roles(),new Questions());
        list.add(us);
        pubD.getNickname().set("marta");
        us = new Utente(pubD,priD,new PW("12345"),new Roles(),new Questions());
        list.add(us);
        pubD.getNickname().set("fil");
        us = new Utente(pubD,priD,new PW("12345"),new Roles(),new Questions());
        list.add(us);
        System.out.println(list.toString());

        /*Test search*/
        String nickSearch[] = {"alfy", "marta", "pippo"};
        Utente usfind;
        System.out.println("\t##Start Search Test##");
        for (int i = 0; i < nickSearch.length ; i++) {
            usfind= list.find(new Nickname(nickSearch[i]));
            if(usfind!=null)
            {
                System.out.println("L'utente "+ nickSearch[i] + " è stato Trovato");
            }else {
                System.out.println("L'utente "+ nickSearch[i] + " non è stato Trovato");

            }
        }
        System.out.println(list.toString());

        /*Test Remove*/
        String nickRemove[] = {"alfy", "fil", "pippo"};

        System.out.println("\t**Start Remove Test**");
        for (int i = 0; i < nickSearch.length ; i++) {
            list.remove(new Nickname(nickRemove[i]));
        }
        System.out.println(list.toString());


    }
}
