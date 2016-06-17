import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import javax.naming.directory.SearchControls;

/**
 * Created by hu on 16/6/14.
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    private int[] edgeTo; //下标表示顶点A,下标对应的数组值表示顶点B, B->A,A可由B到达。

    /*
    *      edgeTo[]
    *      0                    0
    *      1 2                  |
    *      2 0                  2
    *      3 2                 / \
    *      4 3                1   3
    *      5 3                   / \
    *                           4   5
     */
    private final int s; //起点

    public DepthFirstSearch(Graph G, int s)
    {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s=s;
        dfs(G,s);
    }



    private void dfs(Graph G,int v)
    {
        marked[v]=true;
        count++;
        for (int w:G.adj(v))
        {
            if (!marked[w])
            {
                edgeTo[w]=v;
                dfs(G,w);
            }
        }
    }

    public boolean hasPathTo(int v)
    {
        return marked[v];
    }
    public boolean marked(int w)
    {
        return marked[w];
    }

    public int count()
    {
        return count;
    }

    public Iterable<Integer> pathto(int v)
    {
        if (!hasPathTo(v)) return  null;
        Stack<Integer> path = new Stack<Integer>();

        for (int x=v; x!=s; x=edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
    public static void main(String[] args)
    {
        Graph G=new Graph(new In(args[0]));

        int s=Integer.parseInt(args[1]);

        DepthFirstSearch search = new DepthFirstSearch(G,s);

        for (int v=0;v<G.V();v++)
        {
            if (search.marked[v]) StdOut.print(v + " ");

        }
        StdOut.println();

        if (search.count() != G.V()) StdOut.print("NOT ");

        StdOut.println("connected");
    }

}
