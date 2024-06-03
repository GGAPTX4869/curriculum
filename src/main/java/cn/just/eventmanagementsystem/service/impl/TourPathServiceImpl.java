package cn.just.eventmanagementsystem.service.impl;

import cn.just.eventmanagementsystem.common.navigation.MGraph;
import cn.just.eventmanagementsystem.common.navigation.View;
import cn.just.eventmanagementsystem.service.TourPathService;
import lombok.Data;
import java.util.Arrays;

/**
 * 路线业务逻辑接口实现类
 */
@Data
public class TourPathServiceImpl implements TourPathService {
    // 景点图
    private MGraph graph;

    // 用于存储哈密尔顿路径上的顶点编号
    private int[] path;

    // 是否找到哈密尔顿路径
    private boolean isFind;

    /**
     * 构造器
     *
     * @param graph 景点图
     */
    public TourPathServiceImpl(MGraph graph) {
        // 初始化景点图
        this.graph = graph;

        // 初始化哈密尔顿路径(默认为景点图中顶点的个数)
        path = new int[graph.getViews().length];

        // 初始化是否找到哈密尔顿路径(默认为false)
        isFind = false;
    }

    /**
     * 检查顶点v是否可以添加到哈密尔顿循环的索引为pos的位置
     *
     * @param v   顶点编号
     * @param pos 索引
     * @return true：可以添加；false：不可以添加
     */
    private boolean isSafe(int v, int pos) {
        // 如果顶点v与索引为pos-1的顶点之间没有边，则返回false
        if (graph.getMatrix()[path[pos - 1]][v] == 0)
            return false;

        // 如果顶点v已经在哈密尔顿循环中，则返回false
        for (int i = 0; i < pos; i++)
            if (path[i] == v)
                return false;

        // 否则返回true
        return true;
    }

    /**
     * 哈密尔顿循环的递归函数，用于求解哈密尔顿循环
     *
     * @param pos         当前顶点的索引
     * @param lastVisited 上一个访问的顶点
     * @param visited     记录顶点是否已经访问过
     * @return true：找到哈密尔顿循环；false：没有找到哈密尔顿循环
     */
    private boolean hamiltonianCycleUtil(int pos, int lastVisited, boolean[] visited) {
        // 如果所有顶点都已经访问过（即当前顶点的索引等于顶点的个数）
        if (pos == graph.getViews().length) {
            // 检查最后一个顶点与起始顶点之间是否有边
            return graph.getMatrix()[path[pos - 1]][path[0]] != Integer.MAX_VALUE;
        }

        // 遍历所有顶点
        for (int v = 0; v < graph.getViews().length; v++) {
            // 如果顶点v可以添加到哈密尔顿循环的索引为pos的位置(即顶点v与索引为pos-1的顶点之间有边，并且顶点v没有被访问过)
            if ((graph.getMatrix()[path[pos - 1]][v] != Integer.MAX_VALUE) && (!visited[v]) && (v != lastVisited)) {
                // 将顶点v添加到哈密尔顿循环的索引为pos的位置
                path[pos] = v;
                // 将顶点v标记为已访问
                visited[v] = true;

                // 递归调用自身，检查顶点v是否可以添加到哈密尔顿循环的索引为pos+1的位置
                if (hamiltonianCycleUtil(pos + 1, v, visited))
                    return true;

                // 如果顶点v不能添加到哈密尔顿循环的索引为pos+1的位置，则将顶点v从哈密尔顿循环中移除
                path[pos] = -1;
                visited[v] = false;
            }
        }

        return false;
    }

    /**
     * 求哈密尔顿路径
     *
     * @param source 起始顶点的名称
     */
    @Override
    public void hamiltonian(String source) {
        // 获取所有景点
        View[] views = graph.getViews();

        // 获取顶点数
        int num = views.length;

        // 记录经过的顶点是否已经访问过
        boolean[] visited = new boolean[num];

        // 使用 Arrays 的 fill() 方法将 path 数组中的所有元素都设置为 -1
        Arrays.fill(path, -1);
        // 使用 Arrays 的 fill() 方法将 visited 数组中的所有元素都设置为 false
        Arrays.fill(visited, false);

        // 查找起始顶点的编号
        int sourceIndex = -1;
        for (int i = 0; i < num; i++) {
            if (views[i].getName().equals(source)) {
                sourceIndex = i;
                break;
            }
        }

        // 如果起始顶点的编号为 -1，则返回
        if (sourceIndex == -1) {
            System.out.println("没有找到起始顶点！");
            return;
        }

        // 从起始顶点开始构建哈密尔顿循环
        path[0] = sourceIndex;
        // 将起始顶点标记为已访问
        visited[sourceIndex] = true;

        // 如果找到哈密尔顿循环，则打印哈密尔顿路径（从下标为1的位置开始，因为下标为0的位置已经存放了起始顶点的编号）
        if (hamiltonianCycleUtil(1, sourceIndex, visited)) {
            printSolution(path);
        } else {
            System.out.println("没有找到哈密尔顿路径！");
        }
    }

    /**
     * 打印哈密尔顿路径(打印顶点名称)
     *
     * @param path 哈密尔顿路径上的顶点编号
     */
    private void printSolution(int[] path) {
        System.out.println("以下是推荐的路径：");

        for (int j : path) {
            System.out.print(graph.getViews()[j].getName() + " -> ");
        }
        System.out.println(graph.getViews()[path[0]].getName());
    }
}
