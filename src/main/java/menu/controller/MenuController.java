package menu.controller;

import camp.nextstep.edu.missionutils.Randoms;
import menu.domain.Category;
import menu.domain.DayOfWeek;
import menu.domain.coach.Coach;
import menu.view.InputView;
import menu.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static menu.domain.Category.*;
import static menu.domain.DayOfWeek.*;

public class MenuController {

    private static final DayOfWeek[] DAYS_OF_WEEK = {MON, TUE, WED, THU, FRI};

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

        /// 카테고리 및 메뉴 추천
        // 기본 메뉴 등록
        Map<Category, List<String>> menusOfCategory = initializeMenu();

        // 요일별 카테고리 지정
        // 2개 제한용 맵
        Map<Category, Integer> categoriesCount = new HashMap<>();

        // 요구사항에서 제시한 랜덤 카테고리 추출용 맵
        Map<Integer, Category> categories = initializeCategoryForRandom();

        // 요일별 카테고리 생성
        Map<DayOfWeek, Category> categoryOfDay = new HashMap<>();
        for (DayOfWeek day : DAYS_OF_WEEK) {
            while (true) {
                Category randomCategory = categories.get(Randoms.pickNumberInRange(1, 5));

                if (!categoriesCount.containsKey(randomCategory)) {
                    categoryOfDay.put(day, randomCategory);
                    categoriesCount.put(randomCategory, 1);
                    break;
                }

                int previousCount = categoriesCount.get(randomCategory);
                if (previousCount < 2) {
                    categoryOfDay.put(day, randomCategory);
                    categoriesCount.put(randomCategory, previousCount + 1);
                    break;
                }
            }
        }


    }

    private static Map<Integer, Category> initializeCategoryForRandom() {
        Map<Integer, Category> categories = new HashMap<>();
        categories.put(1, JAPANESE);
        categories.put(2, KOREAN);
        categories.put(3, CHINESE);
        categories.put(4, ASIAN);
        categories.put(5, WESTERN);
        return categories;
    }

    private static Map<Category, List<String>> initializeMenu() {
        Map<Category, List<String>> menusOfCategory = new HashMap<>();
        List<String> japaneseFoods = new ArrayList<>(9);
        japaneseFoods.add("규동");
        japaneseFoods.add("우동");
        japaneseFoods.add("미소시루");
        japaneseFoods.add("스시");
        japaneseFoods.add("가츠동");
        japaneseFoods.add("오니기리");
        japaneseFoods.add("하이라이스");
        japaneseFoods.add("라멘");
        japaneseFoods.add("오코노미야끼");

        List<String> koreanFoods = new ArrayList<>(9);
        koreanFoods.add("김밥");
        koreanFoods.add("김치찌개");
        koreanFoods.add("쌈밥");
        koreanFoods.add("된장찌개");
        koreanFoods.add("비빔밥");
        koreanFoods.add("칼국수");
        koreanFoods.add("불고기");
        koreanFoods.add("떡볶이");
        koreanFoods.add("제육볶음");

        List<String> chineseFoods = new ArrayList<>(9);
        chineseFoods.add("깐풍기");
        chineseFoods.add("볶음면");
        chineseFoods.add("동파육");
        chineseFoods.add("짜장면");
        chineseFoods.add("짬뽕");
        chineseFoods.add("마파두부");
        chineseFoods.add("탕수육");
        chineseFoods.add("토마토 달걀볶음");
        chineseFoods.add("고추잡채");

        List<String> asianFoods = new ArrayList<>(9);
        asianFoods.add("팟타이");
        asianFoods.add("카오 팟");
        asianFoods.add("나시고렝");
        asianFoods.add("파인애플 볶음밥");
        asianFoods.add("쌀국수");
        asianFoods.add("똠얌꿍");
        asianFoods.add("반미");
        asianFoods.add("월남쌈");
        asianFoods.add("분짜");

        List<String> westernFoods = new ArrayList<>(9);
        westernFoods.add("라자냐");
        westernFoods.add("그라탱");
        westernFoods.add("뇨끼");
        westernFoods.add("끼슈");
        westernFoods.add("프렌치 토스트");
        westernFoods.add("바게트");
        westernFoods.add("스파게티");
        westernFoods.add("피자");
        westernFoods.add("파니니");

        menusOfCategory.put(JAPANESE, japaneseFoods);
        menusOfCategory.put(KOREAN, koreanFoods);
        menusOfCategory.put(CHINESE, chineseFoods);
        menusOfCategory.put(ASIAN, asianFoods);
        menusOfCategory.put(WESTERN, westernFoods);
        return menusOfCategory;
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
