package menu.controller;

import menu.view.InputView;
import menu.view.OutputView;

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
    }

    private List<String> requestCoachNames() {
        while(true) {
            // 코치 이름 입력 요구 문구 출력
            outputView.printCoachNamesRequest();

            try {
                return inputView.readCoachNames();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
