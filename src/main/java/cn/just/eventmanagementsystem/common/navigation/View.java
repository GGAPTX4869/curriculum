package cn.just.eventmanagementsystem.common.navigation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 景点抽象类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class View {
    // 目的地编号
    private int id;

    // 目的地名称
    private String name;

    // 目的地介绍
    private String introduction;
}
