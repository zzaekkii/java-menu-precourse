package menu.domain.coach;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static menu.domain.DayOfWeek.FRI;
import static org.assertj.core.api.Assertions.assertThat;

class CoachTest {

    @Test
    void 메뉴를_추가하면_코치의_메뉴에_반영된다() {
        Coach testCoach = new Coach("test", new ArrayList<>());
        testCoach.addMenu(FRI, "초코파이");

        assertThat(testCoach.getMenus().get(FRI)).isEqualTo("초코파이");
    }
}