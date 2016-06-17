import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by hu on 16/6/16.
 */
public class DirectedCircle {
    private boolean[] marked;
    private boolean[] onstack;
    private int[] edgeTo;
    private Stack<Integer> circle;

    public DirectedCircle(Digraph G)
    {
        marked = new boolean[G.V()];
        onstack = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int s=0; s< G.V(); s++)
        {
            if (!marked[s])
                dfs(G,s);
        }
    }

    private void dfs(Digraph G, int v)
    {
        onstack[v] = true;
        marked[v] = true;

        for (int w:G.adj(v))
        {
            if (hasCircle()) return;  //之所以在此处返回, 是因为在遍历的过程中,v的某一个邻接点可能发现了一个环
            if (!marked[w])
            {
                edgeTo[w] = v;
                dfs(G, w);
            }
            else if (onstack[w])
            {
                circle = new Stack<Integer>();

                int x = v;
                while (x!=w)
                {
                    circle.push(x);
                    x = edgeTo[x];
                }

                circle.push(w);
                circle.push(v);
            }
        }
        onstack[v] = false;
    }


    public boolean hasCircle()
    {
        return circle !=null;
    }


    public Iterable<Integer> cycle()
    {
        return circle;
    }


    public static void main(String[] args)
    {
        Digraph G = new Digraph(new In(args[0]));

        DirectedCircle dc = new DirectedCircle(G);

        StdOut.println(dc.cycle());
    }
}
