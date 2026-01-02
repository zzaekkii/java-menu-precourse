package menu.controller;

import menu.view.InputView;
import menu.view.OutputView;

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
    }
}
