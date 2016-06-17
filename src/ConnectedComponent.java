import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by hu on 16/6/15.
 */
public class ConnectedComponent {
    private int count;// 图中连通分量的数目
    private int[] id; // 每个顶点所处的连通分量的编号
    private boolean[] marked;// 标注每个被访问过的顶点

    public ConnectedComponent(Graph G)
    {
        id = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v=0;v<G.V();v++)
        {
            if (!marked[v]) //如果一个顶点没有被标注过,说明这个顶点属于一个还未被探测的连通分量,
            // 否则如果这个顶点在深度搜索的过程中一定已经被搜索过了
            {
                dfs(G,v);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v)
    {
        marked[v]=true;
        id[v]=count;  //为被搜索到的顶点分配连通分量id
        for (int w:G.adj(v))
        {
            if (!marked[w])
                dfs(G,w);
        }
    }

    public boolean connected(int v,int w)
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
        Graph G = new Graph(new In(args[0]));
        ConnectedComponent cc = new ConnectedComponent(G);

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
