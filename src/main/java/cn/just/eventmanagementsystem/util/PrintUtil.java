package cn.just.eventmanagementsystem.util;

import cn.just.eventmanagementsystem.model.Team;
import java.util.List;

/**
 * 打印相关工具类
 */
public class PrintUtil {

    /**
     * 打印菜单
     */
    public static void printMenu() {
        System.out.println("—————————————————— 赛事管理系统 ————————————————————————");
        System.out.println("|    序号     |       功能                             |");
        System.out.println("|     0      |     退出系统                            |");
        System.out.println("|     1      |     查看所有参赛队伍                      |");
        System.out.println("|     2      |     新增参赛队伍                         |");
        System.out.println("|     3      |     修改参赛队伍                         |");
        System.out.println("|     4      |     删除参赛队伍                         |");
        System.out.println("|     5      |     查找参赛队伍（队伍编号 —— 二叉排序树）   |");
        System.out.println("|     6      |     查找参赛队伍（学校名称 —— 归并排序）     |");
        System.out.println("|     7      |     查找参赛队伍（赛事类别 —— 归并排序）     |");
        System.out.println("|     8      |     模拟决赛叫号系统                      |");
        System.out.println("|     9      |     查看所有景点信息                      |");
        System.out.println("|     10     |     查看推荐路径（哈密尔顿路径）            |");
        System.out.println("|     11     |     查看推荐赛事路径（最短路径）            |");
        System.out.println("———————————————————————————————————————————————————————");
        System.out.println("请输入您要进行的操作序号：");
    }

    /**
     * 打印所有参赛队伍
     *
     * @param teamList 参赛队伍集合
     */
    public static void printTeamList(List<Team> teamList) {
        System.out.println("序号\t#参赛队编号\t#\t参赛作品名称\t#\t参赛学校\t#\t赛事类别\t#\t参赛者\t#\t指导教师");
        for (int i = 0; i < teamList.size(); i++) {
            System.out.println(
                    i + "\t" +
                            teamList.get(i).getTeamNumber() + "\t" +
                            teamList.get(i).getProjectName() + "\t" +
                            teamList.get(i).getSchoolName() + "\t" +
                            teamList.get(i).getEventCategory() + "\t" +
                            teamList.get(i).getParticipants() + "\t" +
                            teamList.get(i).getGuideTeacher()
            );
        }
    }

    /**
     * 打印队伍信息
     *
     * @param team 队伍
     */
    public static void printTeam(Team team) {
        System.out.println(
                team.getTeamNumber() + "\t" +
                        team.getProjectName() + "\t" +
                        team.getSchoolName() + "\t" +
                        team.getEventCategory() + "\t" +
                        team.getParticipants() + "\t" +
                        team.getGuideTeacher()
        );
    }
}
