package cn.just.eventmanagementsystem.common.room;

import cn.just.eventmanagementsystem.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 决赛叫号室
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalRoom implements Runnable{
    // 决赛室
    private String roomName;
    // 决赛室的队伍
    private List<Team> teamList;

    @Override
    public void run() {
        // 使用同步锁，防止多个线程同时访问（锁住当前对象）
        synchronized(this){
            System.out.println("决赛室" + roomName + "开始叫号");
            for (Team team : teamList) {
                System.out.println("决赛室" + roomName + "叫号：" + team.getTeamNumber());
                try{
                    // 每隔0.5秒叫号一次
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("决赛室" + roomName + "叫号结束");
        }
    }
}
