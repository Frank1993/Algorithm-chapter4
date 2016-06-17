import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * Created by hu on 16/6/14.
 */
public class Graph {
    private final int V; //定点数目
    private int E; //边的数目

    private Bag<Integer>[] adj; //邻接表

    public Graph(int V)
    {
        this.V = V;
        this.E=0;
        adj=(Bag<Integer>[]) new Bag[V];  //创建邻接表
        for (int v=0;v<V; v++)
        {
            adj[v]=new Bag<Integer>();
        }
    }

    public Graph(In in)
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
    public int V() { return V;}
    public int E() {return E;}

    public  void addEdge(int v, int w)
    {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }

    public String toString()
    {
        String s=V + " vertices, " + E + " edges\n";
        for (int v=0;v<V;v++)
        {
            s+=v + ": ";
            for (int w: this.adj(v))
            {
                s+=w+" ";
            }
            s+="\n";
        }
        return s;
    }
}
