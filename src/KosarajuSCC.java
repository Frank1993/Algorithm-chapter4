import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by hu on 16/6/15.
 */
public class KosarajuSCC {
    private int count;// 图中连通分量的数目
    private int[] id; // 每个顶点所处的连通分量的编号
    private boolean[] marked;// 标注每个被访问过的顶点

    public KosarajuSCC(Digraph G)
    {
        id = new int[G.V()];
        marked = new boolean[G.V()];

        DepthFirstOrder order = new DepthFirstOrder(G.reverse());

        for (int s : order.reversePost())
        {
            if (!marked[s])
            {
                dfs(G,s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v)
    {
        marked[v]=true;
        id[v]=count;  //为被搜索到的顶点分配连通分量id
        for (int w:G.adj(v))
        {
            if (!marked[w])
                dfs(G,w);
        }
    }

    public boolean Stronglyconnected(int v,int w)
    {
        return id[v]==id[w];
    }

    public int count()
    {
        return count;
    }
    public int id(int v)
    {
        return id[v];
    }

    public static void main(String[] args)
    {
        String data = new String("/Users/hu/algs4/algs4-data/tinyDG.txt");
        Digraph G = new Digraph(new In(data));
        KosarajuSCC cc = new KosarajuSCC(G);

        int M = cc.count();
        StdOut.println(M+" components: ");


        Bag<Integer>[] components =(Bag<Integer>[]) new Bag[M];

        for (int i = 0; i< M; i++)
        {
            components[i]=new Bag<Integer>();
        }

        for (int v = 0; v< G.V();v++)
        {
            components[cc.id(v)].add(v);
        }

        for (int i =0;i<M;i++)
        {
            for (int v:components[i])
            {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
