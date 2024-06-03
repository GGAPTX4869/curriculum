package cn.just.eventmanagementsystem.common.bst;

import cn.just.eventmanagementsystem.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * 二叉搜索树（Binary Search Tree, BST）节点类，其中包含键值和参赛队伍 {@link Team} 数据
 */
@Data
public class Node {
    // 参赛队伍
    private Team team;
    // 左子节点
    private Node left;
    // 右子节点
    private Node right;

    /**
     * 构造函数
     * @param team 参赛队伍
     */
    public Node(Team team) {
        this.team = team;
    }
}
