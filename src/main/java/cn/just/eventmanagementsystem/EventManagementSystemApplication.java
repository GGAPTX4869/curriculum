package cn.just.eventmanagementsystem;

import cn.just.eventmanagementsystem.common.navigation.ViewAndMGraphInitial;
import cn.just.eventmanagementsystem.common.room.FinalRoom;
import cn.just.eventmanagementsystem.common.room.FinalsCallSystem;
import cn.just.eventmanagementsystem.model.Team;
import cn.just.eventmanagementsystem.repository.TeamRepository;
import cn.just.eventmanagementsystem.service.TourPathService;
import cn.just.eventmanagementsystem.service.ViewsService;
import cn.just.eventmanagementsystem.service.impl.ShortestPathServiceImpl;
import cn.just.eventmanagementsystem.service.impl.TeamServiceImpl;
import cn.just.eventmanagementsystem.service.impl.TourPathServiceImpl;
import cn.just.eventmanagementsystem.service.impl.ViewsServiceImpl;
import cn.just.eventmanagementsystem.util.PrintUtil;
import java.util.Scanner;

public class EventManagementSystemApplication {

    public static void main(String[] args) {

        // 实例化所有需要的对象
        Scanner scanner = new Scanner(System.in);
        TeamServiceImpl teamService = new TeamServiceImpl();
        ViewsService viewsService = new ViewsServiceImpl(ViewAndMGraphInitial.mGraph);
        TourPathService tourPathService = new TourPathServiceImpl(ViewAndMGraphInitial.mGraph);
        ShortestPathServiceImpl shortestPathService = new ShortestPathServiceImpl(ViewAndMGraphInitial.mGraph);

        while (true) {
            // 打印菜单
            PrintUtil.printMenu();

            // 选择菜单
            int choice = scanner.nextInt();

            // 根据选择执行对应的操作
            switch (choice) {
                case 1 -> teamService.showAll();
                case 2 -> {
                    // 获取输入的参赛队伍信息
                    System.out.println("请输入参赛作品名称：");
                    String projectName = scanner.next();

                    System.out.println("请输入参赛学校：");
                    String schoolName = scanner.next();

                    System.out.println("请输入赛事类别：");
                    String eventCategory = scanner.next();

                    System.out.println("请输入参赛者：");
                    String participants = scanner.next();

                    System.out.println("请输入指导教师：");
                    String guideTeacher = scanner.next();

                    // 创建参赛队伍对象(队伍编号自动生成,当前最大编号+1)
                    Team team = new Team(
                            TeamRepository.teamList.get(TeamRepository.teamList.size() - 1).getTeamNumber() + 1,
                            projectName, schoolName, eventCategory, participants, guideTeacher);

                    // 添加参赛队伍
                    teamService.save(team);
                }
                case 3 -> {
                    // 获取元素下标
                    System.out.println("请输入要修改的参赛队伍序号：");
                    int index = scanner.nextInt();

                    // 输出原来的信息
                    System.out.println("该队伍原信息为：");
                    PrintUtil.printTeam(TeamRepository.teamList.get(index));

                    // 获取修改后的信息
                    System.out.println("请输入修改后的参赛作品名称：");
                    String projectName = scanner.next();

                    System.out.println("请输入修改后的参赛学校：");
                    String schoolName = scanner.next();

                    System.out.println("请输入修改后的赛事类别：");
                    String eventCategory = scanner.next();

                    System.out.println("请输入修改后的参赛者：");
                    String participants = scanner.next();

                    System.out.println("请输入修改后的指导教师：");
                    String guideTeacher = scanner.next();

                    // 修改信息
                    TeamRepository.teamList.get(index).setProjectName(projectName);
                    TeamRepository.teamList.get(index).setSchoolName(schoolName);
                    TeamRepository.teamList.get(index).setEventCategory(eventCategory);
                    TeamRepository.teamList.get(index).setParticipants(participants);
                    TeamRepository.teamList.get(index).setGuideTeacher(guideTeacher);

                    // 输出修改后的信息
                    System.out.println("修改后的信息为：");
                    PrintUtil.printTeam(TeamRepository.teamList.get(index));
                }
                case 4 -> {
                    System.out.println("请输入要删除的参赛队伍序号：");

                    // 获取元素下标
                    int index = scanner.nextInt();

                    // 执行删除操作
                    teamService.delete(index);
                }
                case 5 -> {
                    System.out.println("请输入要查找的参赛队伍编号：");
                    String teamNumber = scanner.next();
                    int number = Integer.parseInt(teamNumber);
                    teamService.searchTeamByNumber(number);
                }
                case 6 -> {
                    System.out.println("请输入要查找的参赛队伍学校名称：");
                    String schoolName = scanner.next();
                    teamService.searchTeamsBySchoolName(schoolName);
                }
                case 7 -> {
                    System.out.println("请输入要查找的参赛队伍赛事类别：");
                    String eventCategory = scanner.next();
                    teamService.searchTeamsByEventCategory(eventCategory);
                }
                case 8 -> {
                    // 创建每个决赛室的线程
                    Thread roomThread1 = new Thread(new FinalRoom("决赛室1", FinalsCallSystem.teamList1));
                    Thread roomThread2 = new Thread(new FinalRoom("决赛室2", FinalsCallSystem.teamList2));

                    // 启动线程
                    System.out.println("决赛开始！");
                    roomThread1.start();
                    roomThread2.start();

                    try {
                        // 等待线程结束
                        roomThread1.join();
                        roomThread2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("决赛结束！");
                }
                case 9 -> viewsService.displayAllViews();
                case 10 -> {
                    // 用户输入起点
                    System.out.println("请输入起点：");
                    String start = scanner.next();

                    // 调用哈密尔顿路径算法
                    tourPathService.hamiltonian(start);
                }
                case 11 -> {
                    // 用户输入起点和终点
                    System.out.println("请输入起点：");
                    String start = scanner.next();
                    System.out.println("请输入终点：");
                    String end = scanner.next();

                    // 调用最短路径算法
                    shortestPathService.floyd(start, end);
                }
                case 0 -> System.exit(0);
            }
        }
    }
}
