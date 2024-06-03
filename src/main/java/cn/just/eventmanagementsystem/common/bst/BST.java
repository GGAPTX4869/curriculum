package cn.just.eventmanagementsystem.common.bst;

import cn.just.eventmanagementsystem.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 二叉搜索树（Binary Search Tree, BST）类
 * 说明：二叉搜索树是一种特殊的二叉树，它的左子树的键值小于根节点的键值，右子树的键值大于根节点的键值
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BST {
    // 根节点
    private Node root;

    /**
     * 插入节点
     *
     * @param node 根节点
     * @param team 参赛队伍
     * @return 插入后节点
     */
    public Node insertRec(Node node, Team team) {
        // 如果根节点为空，则直接插入（作为根节点）
        if (node == null) {
            node = new Node(team);
            return node;
        }

        // 如果节点小于根节点，则插入到左子树
        if (team.getTeamNumber() < node.getTeam().getTeamNumber())
            node.setLeft(insertRec(node.getLeft(), team));
            // 如果节点大于根节点，则插入到右子树
        else if (team.getTeamNumber() > node.getTeam().getTeamNumber())
            node.setRight(insertRec(node.getRight(), team));

        // 返回节点
        return node;
    }

    /**
     * 插入一个新的 {@link Team}
     *
     * @param team 参赛队伍
     */
    public void insert(Team team) {
        root = insertRec(root, team);
    }

    /**
     * 查找操作
     *
     * @param root       根节点
     * @param teamNumber 参赛队伍编号
     * @return 参赛队伍
     */
    public Team searchRec(Node root, int teamNumber) {
        // 根是 null 或者队伍编号匹配
        if (root == null || root.getTeam().getTeamNumber() == teamNumber) {
            assert root != null;
            return root.getTeam();
        }

        // 如果队伍编号大于根的队伍编号，则在右子树中查找
        if (root.getTeam().getTeamNumber() < teamNumber)
            // 递归查找
            return searchRec(root.getRight(), teamNumber);

        // 如果队伍编号小于根的队伍编号，则在左子树中查找（递归）
        return searchRec(root.getLeft(), teamNumber);
    }

    /**
     * 查找参赛队伍
     *
     * @param teamNumber 参赛队伍编号
     * @return 参赛队伍
     */
    public Team search(int teamNumber) {
        return searchRec(root, teamNumber);
    }

    /**
     * 计算树的深度
     *
     * @param node       根节点
     * @param teamNumber 参赛队伍编号
     * @param depth      深度
     * @return 深度
     */
    public int calculateDepthRec(Node node, int teamNumber, int depth) {
        // 如果根节点为空，则返回 -1
        if (node == null)
            return -1;

        // 如果队伍编号等于根节点的队伍编号，则返回深度
        if (node.getTeam().getTeamNumber() == teamNumber)
            return depth;

        // 深度
        return  node.getTeam().getTeamNumber() < teamNumber ?
                // 如果队伍编号大于根节点的队伍编号，则在右子树中查找
                calculateDepthRec(node.getRight(), teamNumber, depth + 1) :
                // 如果队伍编号小于根节点的队伍编号，则在左子树中查找
                calculateDepthRec(node.getLeft(), teamNumber, depth + 1);

    }

    /**
     * 计算树的深度
     *
     * @param teamNumber 参赛队伍编号
     * @return 深度
     */
    public int calculateDepth(int teamNumber) {
        // 从根节点开始查找,此时深度为 1
        return calculateDepthRec(root, teamNumber, 1);
    }

    /**
     * 计算平均搜索长度（Average Search Length, ASL）
     *
     * @param teamList 参赛队伍列表
     * @return 平均搜索长度
     */
    public double calculateASL(List<Team> teamList) {
        // 总深度
        int totalDepth = 0;

        // 计算总深度
        for (Team team : teamList)
            totalDepth += calculateDepth(team.getTeamNumber());

        // 返回平均搜索长度 = 总深度 / 参赛队伍数量
        return 1.0 * totalDepth / teamList.size();
    }
}
