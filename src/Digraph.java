import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * Created by hu on 16/6/16.
 */
public class Digraph {
    private int V;
    private Bag<Integer>[] adj;
    private int E;

    public Digraph(int v)
    {
        adj = (Bag<Integer>[])  new Bag[v];
        V = v;
        E = 0;
        for (int i = 0; i<v; i++)
        {
            adj[i] =new  Bag<Integer>();
        }
    }


    public Digraph(In in)
    {
        this(in.readInt());
        int edgeNum =in.readInt();
        for (int i=0;i<edgeNum;i++)
        {
            //添加一个边
            int v= in.readInt();
            int w= in.readInt();
            addEdge(v,w);
        }
    }

    public void addEdge(int v, int w)
    {
        adj[v].add(w);
        E++;
    }

    public int V()
    {
        return V;
    }
    public int E()
    {
        return E;
    }

    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }

    public Digraph reverse()
    {
        Digraph R = new Digraph(V);

        for (int v = 0; v<V; v++)
        {
            for (int w:adj[v])
            {
                R.addEdge(w,v);
            }
        }
        return R;
    }
}
