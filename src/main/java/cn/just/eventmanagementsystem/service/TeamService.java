package cn.just.eventmanagementsystem.service;

import cn.just.eventmanagementsystem.model.Team;

import java.util.List;

/**
 * 对参赛队进行 CRUD （增删改查）操作
 */
public interface TeamService {

    /**
     * 查看所有参赛队伍
     */
    void showAll();

    /**
     * 新增参赛队伍信息
     *
     * @param team 要添加的参赛队伍信息
     */
    void save(Team team);

    /**
     * 根据参赛队伍编号删除参赛队伍信息
     *
     * @param index 要删除的参赛队伍元素索引
     */
    void delete(int index);

    /**
     * 根据参赛队伍编号查看参赛队伍信息
     *
     * @param team 要修改的参赛队伍信息
     */
    void update(Team team);

    /**
     * 根据参赛队伍编号查看参赛队伍信息
     *
     * @param teamNumber 要查看的参赛队伍编号
     */
    void searchTeamByNumber(int teamNumber);

    /**
     * 根据学校名称查看参赛队伍信息
     *
     * @param schoolName 要查看的学校名称
     */
    void searchTeamsBySchoolName(String schoolName);

    /**
     * 根据赛事类别查看参赛队伍信息
     *
     * @param eventCategory 要查看的赛事类别
     */
    void searchTeamsByEventCategory(String eventCategory);

    List<Team> getByEventCategory(String eventCategory);
}
