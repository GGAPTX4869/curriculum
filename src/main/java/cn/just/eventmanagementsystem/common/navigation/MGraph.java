package cn.just.eventmanagementsystem.common.navigation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Arrays;

/**
 * 景点图类，采用邻接矩阵存储，顶点数组存储每一个景点，邻接矩阵存储景点之间的距离。
 * 说明：
 * 1.邻接矩阵：用二维数组存储，数组的行和列分别代表两个顶点，数组元素的值代表两个顶点之间的距离。
 * 2.顶点数组：用一维数组存储，数组的下标代表顶点编号，数组元素的值代表顶点对象。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MGraph {
    // 最大权值，表示两个景点之间不可达
    private static final int INF = Integer.MAX_VALUE;
    // 顶点数组
    private View[] views;
    // 邻接矩阵
    private int[][] matrix;

    /**
     * 构造方法(初始化图的顶点和邻接矩阵)
     * @param size 图的顶点数
     */
    public MGraph(int size) {
        // 初始化顶点数组和邻接矩阵
        views = new View[size];
        matrix = new int[size][size];

        // 遍历邻接矩阵，将所有元素初始化为最大权值
        for (int[] row : matrix)
            Arrays.fill(row, INF);
    }

    /**
     * 添加景点
     * @param i 顶点编号
     * @param view 顶点对象
     */
    public void addView(int i, View view) {
        views[i] = view;
    }

    /**
     * 添加边
     * @param i 顶点编号
     * @param j 顶点编号
     * @param weight 权值
     */
    public void addEdge(int i, int j, int weight){
        // 无向图，两个顶点之间的距离相同
        matrix[i][j] = weight;
        matrix[j][i] = weight;
    }
}
