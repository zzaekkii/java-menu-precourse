package menu.controller;

import menu.domain.coach.Coach;
import menu.view.InputView;
import menu.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class MenuController {

    private final InputView inputView;
    private final OutputView outputView;

    public MenuController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        /// 서비스 시작
        // 서비스 시작 문구 출력
        outputView.printServiceStart();

        // 각 코치 이름 입력 받기
        List<String> names = requestCoachNames();

        // 코치마다 못 먹는 메뉴 입력 받기
        List<Coach> coaches = new ArrayList<>();
        for (String name : names) {
            List<String> dislikes = requestDislikeFoods(name);

            coaches.add(new Coach(name, dislikes));
        }
    }

    private List<String> requestCoachNames() {
        while (true) {
            // 코치 이름 입력 요구 문구 출력
            outputView.printCoachNamesRequest();

            try {
                return inputView.readCoachNames();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            } finally {
                outputView.printBlankLine();
            }
        }
    }

    private List<String> requestDislikeFoods(String name) {
        while (true) {
            // 못 먹는 메뉴 입력 요구 문구 출력
            outputView.printDislikeFoodsRequest(name);

            try {
                return inputView.readDislikeFoods();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            } finally {
                outputView.printBlankLine();
            }
        }
    }
}
