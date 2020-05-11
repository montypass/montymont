package ItisSem3;

import static java.lang.Integer.min;
import java.util.*;
import java.io.File;

public class Prim {

    static int INF = Integer.MAX_VALUE; // "Бесконечность"
    static int vNumber; // количество вершин

    public static int mstPrim(int[][] graph) {
        boolean[] used = new boolean [vNumber]; // массив пометок
        int[] distance = new int [vNumber]; // массив расстояния.

        for(int i = 0;i < vNumber;i++)
            distance[i] = INF;
        distance[0] = 0;

        for (int i = 0;i < vNumber;i++) {
            int v = -1;
            for (int nv = 0; nv < vNumber; nv++) // перебираем вершины
                if (!used[nv] && distance[nv] < INF && (v == -1 || distance[v] > distance[nv])) // выбираем самую близкую непомеченную вершину
                    v = nv;
            if (v == -1) break; // ближайшая вершина не найдена
            used[v] = true; // помечаем ее
            for (int nv = 0; nv < vNumber; nv++)
                if (!used[nv] && graph[v][nv] < INF) // для всех непомеченных смежных
                    distance[nv] = min(distance[nv], graph[v][nv]); // улучшаем оценку расстояния
        }
        int ves = 0;
        for (int v = 0; v < vNumber; v++)
            ves += distance[v];
        return ves;
    }

    public static void main(String[] args) {


        File file = new File("input.txt");
        Scanner in = new Scanner(file);
        String s = in.nextLine();
        int n = s.length();
        String[] str = s.split(" ");
        int i = 0;
        int j;
        int[][] matrix = new int[s.length()][s.length()];
        for (j = 0; j < str.length; j++)
            matrix[i][j] = Integer.parseInt(str[j]);
        i++;
        while (in.hasNext() && i < n) {
            s = in.nextLine();
            str = s.split(" ");
            for (j = 0; j < str.length; j++)
                matrix[i][j] = Integer.parseInt(str[j]);
            i++;
        }
        System.out.println(mstPrim(matrix, n));

    }

}
