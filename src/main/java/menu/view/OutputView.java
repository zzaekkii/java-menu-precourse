package menu.view;

import menu.domain.Category;
import menu.domain.DayOfWeek;
import menu.domain.coach.Coach;

import java.util.List;
import java.util.Map;

import static menu.domain.DayOfWeek.DAYS_OF_WEEK;

public class OutputView {

    private static final String RESULT_PREFIX = "[ ";
    private static final String RESULT_MIDDLE = " | ";
    private static final String RESULT_POSTFIX = " ]";
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printServiceStart() {
        System.out.println("점심 메뉴 추천을 시작합니다.\n");
    }

    public void printCoachNamesRequest() {
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
    }

    public void printErrorMessage(String message) {
        System.out.println(ERROR_MESSAGE_PREFIX + message);
    }

    public void printDislikeFoodsRequest(String name) {
        System.out.println(name + "(이)가 못 먹는 메뉴를 입력해 주세요.");
    }

    public void printBlankLine() {
        System.out.println();
    }

    public void printResult(Map<DayOfWeek, Category> categoryOfDay, List<Coach> coaches) {
        System.out.println("메뉴 추천 결과입니다.");

        // 요일 구분 출력
        System.out.print(RESULT_PREFIX + "구분");
        for (DayOfWeek day : DAYS_OF_WEEK) {
            System.out.print(RESULT_MIDDLE + day.getDescription());
        }
        System.out.println(RESULT_POSTFIX);

        // 요일별 카테고리 출력
        System.out.print(RESULT_PREFIX + "카테고리");
        for (DayOfWeek day : DAYS_OF_WEEK) {
            System.out.print(RESULT_MIDDLE + categoryOfDay.get(day).getDescription());
        }
        System.out.println(RESULT_POSTFIX);

        // 코치별 추천 메뉴 출력
        for (Coach coach : coaches) {
            System.out.print(RESULT_PREFIX + coach.getName());
            for (DayOfWeek day : DAYS_OF_WEEK) {
                System.out.print(RESULT_MIDDLE + coach.getMenus().get(day));
            }
            System.out.println(RESULT_POSTFIX);
        }

        printBlankLine();
    }

    public void printServiceEnd() {
        System.out.println("추천을 완료했습니다.");
    }
}
