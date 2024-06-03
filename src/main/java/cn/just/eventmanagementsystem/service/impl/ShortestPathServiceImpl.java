package cn.just.eventmanagementsystem.service.impl;

import cn.just.eventmanagementsystem.common.navigation.MGraph;
import cn.just.eventmanagementsystem.service.ShortestPathService;
import cn.just.eventmanagementsystem.common.navigation.View;
import lombok.Data;

/**
 * 最短路径业务逻辑接口实现类
 */
@Data
public class ShortestPathServiceImpl implements ShortestPathService {
    // 景点图
    private MGraph graph;

    // 任意两个顶点之间的最短路径信息（即最短路径上的顶点编号）
    private int[][] path;

    // 任意两个顶点之间的最短路径长度(即最短路径的权值和)
    private int[][] dist;

    /**
     * 构造方法
     *
     * @param graph 景点图
     */
    public ShortestPathServiceImpl(MGraph graph) {
        this.graph = graph;

        // 获取景点图中顶点的个数(作为默认的最短路径长度)
        int num = graph.getViews().length;

        // 初始化任意两个顶点之间的最短路径信息
        dist = new int[num][num];
        path = new int[num][num];
    }

    /**
     * 获取任意两个顶点之间的最短路径长度（Floyd算法）
     *
     * @param source      源顶点
     * @param destination 目的顶点
     */
    @Override
    public void floyd(String source, String destination) {
        // 获取所有景点
        View[] views = graph.getViews();
        // 获取景点图中顶点的个数
        int num = views.length;

        // 初始化 dist 和 path 数组
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                // 如果 i 和 j 之间有边，则 dist[i][j] 的值为边的权值
                if (graph.getMatrix()[i][j] != 0) {
                    dist[i][j] = graph.getMatrix()[i][j];
                    // path[i][j] 的值为 j （即 i 到 j 的最短路径上的顶点编号为 j）
                    path[i][j] = j;
                } else {
                    // 如果 i 和 j 之间没有边，则 dist[i][j] 的值为无穷大
                    dist[i][j] = Integer.MAX_VALUE;
                    // path[i][j] 的值为 -1
                    path[i][j] = -1;
                }
            }
        }

        // 记录源顶点和目的顶点的编号
        int sourceIndex = -1;
        int destIndex = -1;

        // 遍历所有景点，找到源顶点和目的顶点的编号
        for (int i = 0; i < num; i++) {
            if (views[i].getName().equals(source)) {
                sourceIndex = i;
            }
            if (views[i].getName().equals(destination)) {
                destIndex = i;
            }
        }

        // 如果源顶点和目的顶点的编号为 -1，则说明源顶点或目的顶点不存在
        if (sourceIndex == -1 || destIndex == -1) {
            System.out.println("源顶点或目的顶点不存在！");
            return;
        }

        // 使用 Floyd 算法求 sourceIndex 和 destIndex 之间的最短路径
        for (int k = 0; k < num; k++) {
            for (int i = 0; i < num; i++) {
                // 如果 i 和 k 之间的最短路径为无穷大，则跳过
                if (dist[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int j = 0; j < num; j++) {
                    // 如果 k 和 j 之间的最短路径为无穷大，则跳过
                    if (dist[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    // 如果 i 和 j 之间的最短路径大于 i 和 k 之间的最短路径加上 k 和 j 之间的最短路径
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        // 更新 i 和 j 之间的最短路径
                        dist[i][j] = dist[i][k] + dist[k][j];
                        // 更新 i 和 j 之间的最短路径上的顶点编号
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        // 打印 sourceIndex 和 destIndex 之间的最短路径长度
        System.out.println("从 " + source + " 到 " + destination + " 的最短路径长度为：" + dist[sourceIndex][destIndex]);

        // 打印 sourceIndex 和 destIndex 之间的最短路径上的名称（通过编号获取名称）
        System.out.print("从 " + source + " 到 " + destination + " 的最短路径为：");
        // 记录当前顶点的编号(初始值为源顶点的编号)
        int index = sourceIndex;
        // 打印源顶点的名称
        System.out.print(views[index].getName());
        // 如果当前顶点的编号不等于目的顶点的编号
        while (index != destIndex) {
            // 获取当前顶点的编号
            index = path[index][destIndex];
            // 打印当前顶点的名称
            System.out.print(" -> " + views[index].getName());
        }
        System.out.println();
    }
}
