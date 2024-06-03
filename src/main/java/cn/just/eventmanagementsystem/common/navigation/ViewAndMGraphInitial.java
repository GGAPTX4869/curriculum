package cn.just.eventmanagementsystem.common.navigation;

/**
 * 初始化景点信息和图
 */
public class ViewAndMGraphInitial {
    // 景点信息
    public static View[] views;

    // 图
    public static MGraph mGraph;

    // 使用静态代码块初始化景点信息和图
    static {
        // 创建 10 个景点
        views = new View[10];
        views[0] = new View(0, "行政楼", "行政办公的地方");
        views[1] = new View(1, "海韵胡", "没事儿去转悠的地方");
        views[2] = new View(2, "图书馆", "进去睡觉的地方");
        views[3] = new View(3, "东食堂", "东区干饭点");
        views[4] = new View(4, "东操场", "东区训练地");
        views[5] = new View(5, "南门", "此处容易翻墙");
        views[6] = new View(6, "文体中心", "里面全是秀儿");
        views[7] = new View(7, "西操场", "西区训练地");
        views[8] = new View(8, "经世楼", "上课的地方");
        views[9] = new View(9, "文理大楼", "校园最高的建筑");

        // 创建图
        mGraph = new MGraph(views.length);

        // 添加顶点
        for (int i = 0; i < views.length; i++) {
            mGraph.addView(i, views[i]);
        }

        // 添加边和权值（无向图）
        // 0 行政楼 1 海运胡（同时也就是 1 海运胡 0 行政楼）
        mGraph.addEdge(0, 1, 300);
        // 0 行政楼 9 纹理大楼
        mGraph.addEdge(0, 9, 700);
        // 1 海运胡 2 图书馆
        mGraph.addEdge(1, 2, 600);
        // 2 图书馆 3 东食堂
        mGraph.addEdge(2, 3, 100);
        // 2 图书馆 9 纹理大楼
        mGraph.addEdge(2, 9, 400);
        // 2 图书馆 7 西操场
        mGraph.addEdge(2, 7, 550);
        // 3 东食堂 4 东操场
        mGraph.addEdge(3, 4, 100);
        // 3 东食堂 6 文体中心
        mGraph.addEdge(3, 6, 550);
        // 4 东操场 5 南门
        mGraph.addEdge(4, 5, 250);
        // 5 南门 6 文体中心
        mGraph.addEdge(5, 6, 250);
        // 6 文体中心 7 西操场
        mGraph.addEdge(6, 7, 100);
        // 7 西操场 8 经世楼
        mGraph.addEdge(7, 8, 100);
        // 8 经世楼 9 纹理大楼
        mGraph.addEdge(8, 9, 100);
    }
}
