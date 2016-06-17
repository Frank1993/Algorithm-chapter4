import edu.princeton.cs.algs4.StdOut;

/**
 * Created by hu on 16/6/16.
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph G)
    {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];

        for (int s = 0; s < G.V(); s++)
        {
            if (!marked[s])
                dfs(G,s);
        }
    }

    private void dfs(Graph G, int v)
    {
        marked[v]=true;
        for (int w:G.adj(v))
        {
            if (!marked[w])
            {
                color[w] = !color[v];
                dfs(G,w);

            }else if (color[w] ==color[v])
            {
                isTwoColorable = false;
            }

        }
    }

    public boolean isBiPartite(){
        return isTwoColorable;
    }


    public static void main(String[] args)
    {
        Graph G = new Graph(13);
        G.addEdge(0,1);
        G.addEdge(0,2);
        G.addEdge(0,5);
        G.addEdge(0,6);
        G.addEdge(1,3);
        G.addEdge(4,5);
        G.addEdge(6,7);
        G.addEdge(7,8);
        G.addEdge(8,10);
        G.addEdge(9,10);
        G.addEdge(9,11);
        G.addEdge(11,12);
        G.addEdge(10,12);
        G.addEdge(3,5);
        G.addEdge(4,6);
        G.addEdge(0,3);

        TwoColor twoColor = new TwoColor(G);
        StdOut.println(twoColor.isBiPartite());
    }
}
