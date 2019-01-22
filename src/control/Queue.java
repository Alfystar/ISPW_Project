package control;
import entity.*;

import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Queue {
    private LinkedList<NodeQueue> list= new LinkedList<>();

    private static class LazyCointainer{
        public final static Queue queueSigletonInstance = new Queue();
    }

    static Queue queue = new Queue();

    protected Queue()
    {

    }

    public static final Queue getQueueSingletonInstance() {
        return LazyCointainer.queueSigletonInstance;
    }

    public void add(Utente us)
    {
        NodeQueue node= new NodeQueue(us);
        this.list.addLast(node);
    }

    public Utente find(Nickname nk)
    {
        NodeQueue node = searchInQueue(nk);
        if(node!=null) return node.getUs();
        else return null;
    }

    public void remove(Nickname nk)
    {
        NodeQueue node = searchInQueue(nk);
        if(node!=null)
        {
            node.deleteInfo();
            this.list.remove(node);
        }
    }

    private NodeQueue searchInQueue(Nickname nk)
    {
        String nick = nk.get();
        NodeQueue node;
        for (int i = 0; i < this.list.size(); i++) {
            node=this.list.get(i);
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
        out="#Start List, size=" + this.list.size()+"\n";
        for (int i = 0; i < this.list.size(); i++) {
            node=this.list.get(i).;
            out+= node.toString() + "\n";
        }

        out+="#End List#\n";
        return out;
    }

    public static void Main(String Argv[])
    {
        PublicData pubD = new PublicData(new Name("ema"), new Name("alf"), new TaxCode("lfm"), new Nickname("alfy") ,new Email("ema@gmail.com"), new GregorianCalendar(97,7,31), Gender.MAN));
        PrivateData priD = new PrivateData();
        Utente us1 = new Utente(pubD,priD,new PW("12345"),new Roles(),new Questions());
    }


}
