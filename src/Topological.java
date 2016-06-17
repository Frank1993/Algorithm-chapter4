import edu.princeton.cs.algs4.StdOut;
/**
 * Created by hu on 16/6/16.
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph G)
    {

        DirectedCircle circle = new DirectedCircle(G);

        if (!circle.hasCircle())
        {
            DepthFirstOrder dfo = new DepthFirstOrder(G);
            order = dfo.reversePost();
        }
    }

    public Iterable<Integer> order()
    {
        return order;
    }

    public boolean isDAG()
    {
        return order != null;  //如果不是有向无环图,那么构造函数中的order就不会被初始化
    }


    public static void main(String[] args)
    {
        String filename = args[0];
        String separator = args[1];

        SymbolDigraph sg = new SymbolDigraph(filename,separator);

        Topological top = new Topological(sg.G());

        for (int v : top.order())
        {
            StdOut.println(sg.name(v));
        }
    }

}
