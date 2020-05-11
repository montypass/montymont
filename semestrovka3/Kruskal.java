package ItisSem3;

import static java.util.Arrays.sort;

//DSF - система непрекращающихся множеств.
public class Kruskal {
    static int vNum;

    public static int mstKruskal(Edge[] edges) {
        DSF dsf = new DSF(vNum); // СНМ
        sort(edges); // Сортируем ребра
        int ret = 0; // результат
        for (Edge e : edges) // перебираем ребра в порядке возрастания
            if (dsf.union(e.u, e.v)) // если ребра принадлежат разным компонентам
                ret += e.w; // добавляем вес ребра к стоимости MST
        return ret;
    }

    // Класс ребра
    public static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int w;

        // Конструктор
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge edge) {
            if (w != edge.w) return w < edge.w ? -1 : 1;
            return 0;
        }
    }

    /* Класс СНМ */
    public static class DSF {
        int[] set; // номер множества
        int[] rnk; // ранг

        public DSF(int size) {
            set = new int[size];
            rnk = new int[size];
            for (int i = 0; i < size; i++)
                set[i] = i;
        }

        /* Возвращает множество, которому принадлежит x */
        public int set(int x) {
            return x == set[x] ? x : (set[x] = set(set[x]));
        }

        /* Если u и v лежат в разных множествах, то сливаем их и возвращаем true */
        public boolean union(int u, int v) {
            if ((u = set(u % set.length)) == (v = set(v % set.length)))
                return false;
            if (rnk[u] < rnk[v]) {
                set[u] = v;
            } else {
                set[v] = u;
                if (rnk[u] == rnk[v])
                    rnk[u]++;
            }
            return true;
        }
    }

    public static void main(String[] args) {

                vNum = 6;
        Edge[] edge = new Edge[6];
        edge[0] = new Edge(1,2,3);
        edge[1] = new Edge(2,3,4);
        edge[2] = new Edge(3,4,5);
        edge[3] = new Edge(4,5,6);
        edge[4] = new Edge(5,6,7);
        edge[5] = new Edge(6,1,7);
        System.out.println(mstKruskal(edge));


    }
}