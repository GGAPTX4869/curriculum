package cn.just.eventmanagementsystem.model;

import lombok.*;

/**
 * 参赛队伍类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    // 参赛队伍编号
    private int teamNumber;

    // 项目名称
    private String projectName;

    // 学校名称
    private String schoolName;

    // 赛事类别
    private String eventCategory;

    // 参赛队员
    private String participants;

    // 指导老师
    private String guideTeacher;
}
