package cn.just.eventmanagementsystem.service;

/**
 * 最短路径业务逻辑接口
 * 说明：提供求图中任意两个顶点之间的最短路径的 Floyd 算法，Floyd算法是一种用来找出给定的加权图中顶点间最短路径的算法
 */
public interface ShortestPathService {

    /**
     * 求图中任意两个顶点之间的最短路径(Floyd算法)
     */
    void floyd(String source, String destination);
}
