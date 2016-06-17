/**
 * Created by hu on 16/6/16.
 */
public class Circle {
    private boolean[] marked; //标记已经访问过的顶点

    private boolean hasCircle;

    public Circle(Graph G)
    {
        marked = new boolean[G.V()];

        for (int s=0; s<G.V(); s++)
        {
            if (!marked[s])
                dfs(G,s,s);   //每一个s都是一个连通分量中第一个被访问的顶点,
        }
    }

    private void dfs(Graph G, int v,int u)
    {
        //表示v是u访问的
        marked[v]=true;
        for (int w:G.adj(v))
        {
            if (!marked[w])
                dfs(G,w,v);
            else if (w != u)
                hasCircle = true;
        }

    }

    public boolean hasCircle()
    {
        return hasCircle;
    }




}
