import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * Created by hu on 16/6/16.
 */
public class SymbolGraph {
    private ST<String,Integer> st;  //符号名——>索引
    private String[] keys;  //索引 ——> 符号名
    private Graph G;   //以索引表示顶点的图

    public SymbolGraph(String stream, String sp)
    {
        st=new ST<String,Integer>();

        In in = new In(stream);    //第一遍遍历数据
        while (in.hasNextLine())   //构造索引
        {
            String[] a=in.readLine().split(sp);  //读取一行字符串,以sp为分隔符进行分割

            for(int i = 0; i<a.length; i++)
            {
                if (!st.contains(a[i]))    //为每个不同的字符串关联一个索引
                    st.put(a[i],st.size());
            }
        }

        keys = new String[st.size()];   //用来获取顶点名的反向索引是一个数组,而不是符号表
        for (String name:st.keys())
        {
            keys[st.get(name)]=name;
        }

        G = new Graph(st.size());

        in = new In(stream);      //第二遍遍历数据
        while (in.hasNextLine())    //利用数据查找其对应的索引来构造图
        {
            String[] a = in.readLine().split(sp);   //将每一行的第一个顶点和该行的其他顶点相连
            int v = st.get(a[0]);
            for (int i = 1; i< a.length; i++)
            {
                G.addEdge(v,st.get(a[i]));
            }
        }

    }

    public boolean contains(String s) {return st.contains(s);}

    public int index(String s) {return st.get(s);}

    public String name(int v) {return keys[v];}

    public Graph G() {return  G;}



}
