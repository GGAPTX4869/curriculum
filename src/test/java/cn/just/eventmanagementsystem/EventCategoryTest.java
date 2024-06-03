package cn.just.eventmanagementsystem;

import cn.just.eventmanagementsystem.model.Team;
import cn.just.eventmanagementsystem.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest(classes = EventManagementSystemApplication.class)
public class EventCategoryTest {

    @Test
    void testEventCategoryNum() {
        List<Team> teamList = TeamRepository.teamList;

        // 元素单一
        Set<String> category = new HashSet<>();

        for (int i = 0; i < teamList.size(); i++) {
            category.add(teamList.get(i).getEventCategory());
        }

        System.out.println("category.size() = " + category.size());
        for (String s : category) {
            System.out.println(s);
        }
    }
}
