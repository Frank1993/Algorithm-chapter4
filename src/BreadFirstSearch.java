import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by hu on 16/6/15.
 */
public class BreadFirstSearch {
    private boolean[] marked; //标注已经访问过的顶点
    private int[] edgeTo;
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
    private  final int s; //起点

    private int count; //起点s能够连通多少个顶点

    public BreadFirstSearch(Graph G, int s)
    {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s =s;
        this.count = 0;
        bfs(G,s);
    }

    private void bfs(Graph G, int s)
    {
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;
        count +=1;
        queue.enqueue(s);

        while (!queue.isEmpty())
        {
            int v = queue.dequeue();
            for (int w : G.adj(v))
            {
                if (!marked[w])
                {
                    marked[w] = true;
                    count +=1;
                    edgeTo[w] = v;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v)
    {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v)
    {
        if (!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<Integer>();
        while (v!=s)
        {
            path.push(v);
            v = edgeTo[v];
        }

        path.push(s);
        return  path;
    }

    public int count() {return count;}
    public static void main(String[] args)
    {
        Graph G=new Graph(new In(args[0]));

        int s=Integer.parseInt(args[1]);

        BreadFirstSearch search = new BreadFirstSearch(G,s);

        for (int v=0;v<G.V();v++)
        {
            if (search.marked[v]) StdOut.print(v + " ");

        }

        StdOut.println();

        if (search.count() != G.V()) StdOut.print("NOT ");

        StdOut.println("connected");

        for (int v = 0; v<G.V();v++)
        {
            StdOut.print(s + "to "+ v+": ");
            if (search.hasPathTo(v))
                for (int x : search.pathTo(v))
                    if (x==s) StdOut.print(x);
                    else StdOut.print("-"+x);
            StdOut.println();
        }
    }
}
