package cn.just.eventmanagementsystem.service;

/**
 * 赛事路径业务逻辑接口
 * 说明：提供了求推荐赛事路径（哈密尔顿路径）的马踏棋盘算法
 * 1.哈密尔顿路径：图中经过每个顶点一次且仅一次的路径
 * 2.马踏棋盘算法：从图中任意一个顶点出发，每次走一步，走遍图中所有顶点，且每个顶点只走一次，由此得到一条哈密尔顿路径
 */
public interface TourPathService {

    /**
     * 求哈密尔顿路径
     */
    void hamiltonian(String source);
}
