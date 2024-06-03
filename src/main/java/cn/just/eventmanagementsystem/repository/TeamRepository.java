package cn.just.eventmanagementsystem.repository;

import cn.just.eventmanagementsystem.model.Team;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 存储参赛队伍信息
 * 容器的选择：
 * List 集合：单列集合 team（单个个体） - 允许存入重复元素
 * Set 集合：单列集合 team（单个个体） - 不允许存入重复元素
 * Map 集合：双列集合（key-value）  性别（key） - 女（value）
 */
public class TeamRepository {
    // 存放所有参赛队伍信息的集合(List Set Map)
    public static List<Team> teamList = new ArrayList<>();

    // 静态代码块：在类加载时将文件中的信息读取到集合中
    static{
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/team.txt"), "GBK"))){

            // 读取文件中的所有参赛队伍信息（按行读取）
            String line = null;

            // 循环读取每一行
            while((line = reader.readLine()) != null){

                // 从第二行开始读取(第一行是无关信息）
                if(line.startsWith("参赛队编号")) continue;

                // 将每一行的信息存入数组
                String[] teamInfo = line.split("#");

                // 去除字符串左右两边的空格
                for (int i = 0; i < teamInfo.length; i++)
                    teamInfo[i] = teamInfo[i].trim();


                // 将 Team 对象存入集合
                teamList.add(new Team(Integer.parseInt(teamInfo[0]), teamInfo[1], teamInfo[2], teamInfo[3], teamInfo[4], teamInfo[5]));

                // 根据队伍编号从小到大进行排序（底层使用的是归并排序）
                // 注意：排序后会影响二叉排序树算法的平均查找长度 ASL，因为二叉排序树算法是根据队伍编号从小到大进行查找的
                //      如果这里进行了排序，那么二叉排序树算法的 ASL 就会变大。
                teamList.sort(Comparator.comparingInt(Team::getTeamNumber));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
