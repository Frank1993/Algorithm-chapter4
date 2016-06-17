import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by hu on 16/6/16.
 */

public class DepthFirstOrder {
    //一个有向图的三种排序方法   先序/后序/逆后序

    private boolean[] marked;
    private Queue<Integer> pre;  //所有顶点的先序排列
    private Queue<Integer> post;  //所有顶点的后序排列
    private Stack<Integer> reversePost;  //所有顶点的逆后序排列

    public DepthFirstOrder(Digraph G)
    {
        marked = new boolean[G.V()];
        pre = new Queue<Integer>();
        post  = new Queue<Integer>();
        reversePost = new Stack<Integer>();


        for (int s = 0; s< G.V(); s++)
        {
            if (!marked[s])
                dfs(G,s);
        }
    }

    private void dfs(Digraph G, int v)
    {
        pre.enqueue(v);

        marked[v] = true;
        for (int w: G.adj(v))
        {
            if (!marked[w])
                dfs(G,w);
        }

        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre()
    {
        return pre;
    }

    public Iterable<Integer> post()
    {
        return post;
    }

    public Iterable<Integer> reversePost()
    {
        return reversePost;
    }


    public static void main(String[] args)
    {
        Digraph G = new Digraph(new In(args[0]));

     //   Digraph G = new Digraph(new In("/Users/hu/algs4/algs4-data/tinyDG.txt"));
        DepthFirstOrder dfo = new DepthFirstOrder(G);

        StdOut.println("Pre: ");
        for(int v : dfo.pre())
        {
            StdOut.print(v+" ");
        }
        StdOut.println();

        StdOut.println("post: ");
        for(int v : dfo.post())
        {
            StdOut.print(v+" ");
        }
        StdOut.println();

        StdOut.println("reversePost: ");
        for(int v : dfo.reversePost())
        {
            StdOut.print(v+" ");
        }
        StdOut.println();
    }
}
