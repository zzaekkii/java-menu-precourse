package menu.view;

public class OutputView {
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
}
