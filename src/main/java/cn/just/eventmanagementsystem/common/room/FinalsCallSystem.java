package cn.just.eventmanagementsystem.common.room;

import cn.just.eventmanagementsystem.model.Team;
import cn.just.eventmanagementsystem.repository.TeamRepository;
import cn.just.eventmanagementsystem.service.TeamService;
import cn.just.eventmanagementsystem.service.impl.TeamServiceImpl;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * 决赛叫号系统
 */
@Data
public class FinalsCallSystem {
    // 决赛室 1
    public static List<Team> teamList1 = new ArrayList<>();
    // 决赛室 2
    public static List<Team> teamList2 = new ArrayList<>();
    // 队伍业务逻辑接口
    private static TeamService teamService = new TeamServiceImpl();

    // 静态代码块(为每个决赛室添加对应的参赛队伍)
    static {
        // 查询所有的 平面设计 赛事参赛队伍
        List<Team> designTeamList = teamService.getByEventCategory("平面设计");
        // 查询所有的 网页设计 赛事参赛队伍
        List<Team> webTeamList = teamService.getByEventCategory("环境设计");

        // 将 平面设计 赛事参赛队伍添加到决赛室 1
        teamList1.addAll(designTeamList);
        // 将 网页设计 赛事参赛队伍添加到决赛室 2
        teamList2.addAll(webTeamList);
    }
}
