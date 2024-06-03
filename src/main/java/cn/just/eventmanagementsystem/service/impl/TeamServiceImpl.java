package cn.just.eventmanagementsystem.service.impl;

import cn.just.eventmanagementsystem.common.bst.BST;
import cn.just.eventmanagementsystem.model.Team;
import cn.just.eventmanagementsystem.repository.TeamRepository;
import cn.just.eventmanagementsystem.service.TeamService;
import cn.just.eventmanagementsystem.util.PrintUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 队伍业务逻辑实现类
 */
public class TeamServiceImpl implements TeamService {

    /**
     * 查看所有参赛队伍信息
     */
    @Override
    public void showAll() {
        PrintUtil.printTeamList(TeamRepository.teamList);
    }

    /**
     * 查找参赛队伍信息表的指定元素
     *
     * @param team 要查找的参赛队伍信息
     * @return >= 0: 对应元素索引; -1: 没有搜索到
     */
    private int findTeam(Team team) {
        // 使用二分查找是否存在相同的参赛队伍编号(返回的是 Key 的索引，如果不存在则返回 -1)
        return Collections.binarySearch(TeamRepository.teamList, team, Comparator.comparingInt(Team::getTeamNumber));
    }

    /**
     * 添加参赛队伍信息
     *
     * @param team 要添加的参赛队伍信息
     */
    @Override
    public void save(Team team) {
        // 如果存在相同的参赛队伍编号
        if (findTeam(team) > 0) {
            // 打印错误信息
            System.out.println("参赛队伍编号已存在, 请重新输入!");
            // 退出方法
            return;
        }

        // 将 Team 对象存入集合
        TeamRepository.teamList.add(team);

        // 输出添加后的信息
        System.out.println("添加成功!");
        System.out.println("添加后的信息为：");
        PrintUtil.printTeam(team);
    }

    /**
     * 根据参赛队伍编号删除参赛队伍信息
     *
     * @param index 要删除的参赛队伍元素索引
     */
    @Override
    public void delete(int index) {
        System.out.println("被删除的参赛队伍信息: ");
        PrintUtil.printTeam(TeamRepository.teamList.get(index));
        System.out.println("正在删除...");

        TeamRepository.teamList.remove(index);
        System.out.println("删除成功!");
    }

    /**
     * 修改参赛队伍信息
     *
     * @param team 要修改的参赛队伍信息
     */
    @Override
    public void update(Team team) {
        // 查找出对应的参赛队伍元素索引
        int index = findTeam(team);

        // 如果不存在相同的参赛队伍编号
        if (index < 0) {
            // 打印错误信息
            System.out.println("参赛队伍编号不存在, 请重新输入!");
            // 退出方法
            return;
        }

        // 修改参赛队伍信息
        TeamRepository.teamList.set(index, team);
    }

    /**
     * 根据参赛队伍编号查看参赛队伍信息
     * 说明：使用基于二叉排序树的查找算法，若查找成功则返回参赛队伍信息，同时输出查找成功时的平均查找长度 ASL，
     *
     * @param teamNumber 要查看的参赛队伍编号
     */
    @Override
    public void searchTeamByNumber(int teamNumber) {
        // 实例化二叉排序树（BST）
        BST bst = new BST();

        // 填充二叉排序树（BST）
        for (Team team : TeamRepository.teamList) {
            bst.insert(team);
        }

        // 查找参赛队伍信息
        Team team = bst.search(teamNumber);

        // 若查找失败则输出 ”查找失败“，否则输出查找成功时的平均查找长度 ASL
        if (team == null) {
            System.out.println("查找失败");
        } else {
            // 输出查找到的参赛队伍信息
            System.out.println("--------------------- 查询结果 --------------------");
            System.out.println("查找成功，平均查找长度 ASL = 总深度 / 参赛队伍数量 = " + bst.calculateASL(TeamRepository.teamList));
            PrintUtil.printTeam(team);
        }
    }

    /**
     * 根据参赛队伍名称查看参赛队伍信息
     * 说明：用归并排序（Merge Sort）来进行赛事类别（eventCategory）的排序，
     * 因为归并排序对于大的数据集来说非常高效，时间复杂度为O(n log n)，并且它是稳定的排序算法，
     * 这意味着相同的元素在排序后会保持原有的顺序。
     *
     * @param schoolName 要查看的学校名称
     */
    @Override
    public void searchTeamsBySchoolName(String schoolName) {
        // 声名一个新的数组，用于存放查找到的参赛队伍信息
        List<Team> schoolTeams = new ArrayList<>();

        // 遍历列表查找要查看的参赛队伍信息
        for (Team team : TeamRepository.teamList) {
            // 如果属于同一个学校，则将其添加到新的数组中
            if (team.getSchoolName().trim().equals(schoolName.trim())) {
                schoolTeams.add(team);
            }
        }

        // 未找到对应的团队
        if (schoolTeams.isEmpty()) {
            System.out.println("没有找到对应的团队");
            return;
        }

        // 对 schoolTeams 按 eventCategory 归并排序
        schoolTeams = mergeSort(schoolTeams);

        // 输出团队基本信息
        System.out.println("--------------------- 查询结果 --------------------");
        PrintUtil.printTeamList(schoolTeams);
    }

    /**
     * 根据赛事类别查看参赛队伍信息
     *
     * @param eventCategory 要查看的赛事类别
     */
    @Override
    public void searchTeamsByEventCategory(String eventCategory) {
        // 声名一个新的数组，用于存放查找到的参赛队伍信息
        List<Team> eventCategoryTeams = new ArrayList<>();

        // 遍历列表查找要查看的参赛队伍信息
        for (Team team : TeamRepository.teamList) {
            // 如果属于同一个学校，则将其添加到新的数组中
            if (team.getEventCategory().trim().equals(eventCategory.trim())) {
                eventCategoryTeams.add(team);
            }
        }

        // 未找到对应的团队
        if (eventCategoryTeams.isEmpty()) {
            System.out.println("没有找到对应的团队");
            return;
        }

        // 对 schoolTeams 按 eventCategory 归并排序
        eventCategoryTeams = mergeSort(eventCategoryTeams);

        // 输出团队基本信息
        System.out.println("--------------------- 查询结果 --------------------");
        PrintUtil.printTeamList(eventCategoryTeams);
    }

    public List<Team> getByEventCategory(String eventCategory) {
        // 声名一个新的数组，用于存放查找到的参赛队伍信息
        List<Team> eventCategoryTeams = new ArrayList<>();

        // 遍历列表查找要查看的参赛队伍信息
        for (Team team : TeamRepository.teamList) {
            // 如果属于同一个学校，则将其添加到新的数组中
            if (team.getEventCategory().trim().equals(eventCategory.trim())) {
                eventCategoryTeams.add(team);
            }
        }

        // 未找到对应的团队
        if (eventCategoryTeams.isEmpty()) {
            return null;
        }

        // 对 schoolTeams 按 eventCategory 归并排序
        eventCategoryTeams = mergeSort(eventCategoryTeams);

        return eventCategoryTeams;
    }

    /**
     * 归并排序
     *
     * @param teams 要排序的参赛队伍信息
     * @return 排序后的参赛队伍信息
     */
    private static List<Team> mergeSort(List<Team> teams) {
        // 如果列表为空或者只有一个元素，则直接返回
        if (teams.size() < 2) {
            return teams;
        }

        // 将列表分为两部分
        int midIndex = teams.size() / 2;
        // 左边的列表
        List<Team> left = new ArrayList<>(teams.subList(0, midIndex));
        // 右边的列表
        List<Team> right = new ArrayList<>(teams.subList(midIndex, teams.size()));

        // 调用递归函数，分别对左右两边的列表进行排序
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 合并两个有序列表
     *
     * @param left  左边的列表
     * @param right 右边的列表
     * @return 合并后的列表
     */
    private static List<Team> merge(List<Team> left, List<Team> right) {
        // 合并后的列表
        List<Team> merged = new ArrayList<>();

        // 初始化左右两个列表的索引
        int leftIndex = 0, rightIndex = 0;

        // 循环比较两个列表的元素
        while (leftIndex < left.size() && rightIndex < right.size()) {
            // 如果左边的元素小于等于右边的元素，则将左边的元素添加到合并后的列表中
            if (left.get(leftIndex).getEventCategory().compareTo(right.get(rightIndex).getEventCategory()) <= 0) {
                merged.add(left.get(leftIndex++));
            } else {
                // 否则将右边的元素添加到合并后的列表中
                merged.add(right.get(rightIndex++));
            }
        }

        // 将剩余的元素添加到合并后的列表中
        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex++));
        }

        // 将剩余的元素添加到合并后的列表中
        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex++));
        }

        // 返回合并后的列表
        return merged;
    }
}
