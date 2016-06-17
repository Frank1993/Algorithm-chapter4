import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by hu on 16/6/16.
 */
public class DegreeOfSeparation {
    public static void main(String[] args)
    {
        SymbolGraph sg = new SymbolGraph(args[0],args[1]);

        Graph G = sg.G();

        String source = args[2];

        if (!sg.contains(source))
        {
            StdOut.println(source+ "not in database." );
            return;
        }

        int s = sg.index(source);

        BreadFirstSearch bfs = new BreadFirstSearch(G,s);

        while (!StdIn.isEmpty())
        {
            String sink = StdIn.readLine();
            if (sg.contains((sink)))
            {
                int t = sg.index(sink);
                if (bfs.hasPathTo(t))
                {
                    for (int v: bfs.pathTo(t))
                        StdOut.println("  "+sg.name(v));
                }else StdOut.println("Not Connected");
            }else
            {
                StdOut.println("Not in databases.");
            }
        }
    }
}