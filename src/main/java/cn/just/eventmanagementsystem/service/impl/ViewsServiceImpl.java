package cn.just.eventmanagementsystem.service.impl;

import cn.just.eventmanagementsystem.common.navigation.MGraph;
import cn.just.eventmanagementsystem.common.navigation.View;
import cn.just.eventmanagementsystem.service.ViewsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 视图业务逻辑接口实现类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewsServiceImpl implements ViewsService {
    // 景点图
    private MGraph graph;

    /**
     * 显示所有景点信息
     */
    @Override
    public void displayAllViews() {
        System.out.println("------------------  景点信息  ------------------");
        for (View view : graph.getViews()) {
            System.out.println("ID: " + view.getId());
            System.out.println("Name: " + view.getName());
            System.out.println("Introduction: " + view.getIntroduction());
            System.out.println("----------------------");
        }
    }
}
