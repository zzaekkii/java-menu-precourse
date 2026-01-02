package menu.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class InputView {

    private static final String SEPARATOR = ",";

    public List<String> readCoachNames() {
        String input = readLine();

        nullAndBlankCheck(input);

        input = input.trim();

        validateSeparatorPattern(input);

        return separateNames(input);
    }

    public List<String> readDislikeFoods() {
        String input = readLine();

        // 빈 값 입력 가능
        nullCheck(input);

        input = input.trim();

        validateSeparatorPattern(input);

        return separateFoods(input);
    }

    // 굳이 Optional<> 안 해도 될 듯
    // 빈 리스트를 넣으면 되니까
    private List<String> separateFoods(String input) {
        String[] tokens = input.split(SEPARATOR);

        List<String> foods = new ArrayList<>();

        for (String token : tokens) {
            nullCheck(token);

            // `파인애플 볶음밥`이나 `토마토 달걀볶음`의 경우 공백이 포함됨
            if (!token.matches("^[가-힣 ]+$")) {
                throw new IllegalArgumentException("한글로 입력해야 합니다.");
            }

            foods.add(token);
        }

        if (foods.stream().distinct().count() > 2) {
            throw new IllegalArgumentException("못 먹는 메뉴는 최대 2개까지 입력 가능합니다.");
        }

        return foods.stream().distinct().toList();
    }

    private static String readLine() {
        return Console.readLine();
    }

    private static void nullAndBlankCheck(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값이 존재하지 않습니다.");
        }
    }

    private static void nullCheck(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력값이 존재하지 않습니다.");
        }
    }

    private static void validateSeparatorPattern(String value) {
        // 앞/뒤에 등장하지 않아야 함
        if (value.startsWith(",") || value.endsWith(",")) {
            throw new IllegalArgumentException("구분자 형식이 맞지 않습니다.");
        }

        // 연속
        if (value.contains(",,")) {
            throw new IllegalArgumentException("구분자가 연속되어 있습니다.");
        }
    }

    private List<String> separateNames(String input) {
        String[] tokens = input.split(SEPARATOR);

        List<String> names = new ArrayList<>();

        for (String token : tokens) {
            nullCheck(token);

            if (!token.matches("^[가-힣]+$")) {
                throw new IllegalArgumentException("한글만 입력해야 합니다.");
            }

            if (token.length() > 4) {
                throw new IllegalArgumentException("이름은 4글자까지만 등록 가능합니다.");
            }

            names.add(token);
        }

        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException("중복된 이름이 있습니다.");
        }

        if (names.size() > 5 || names.size() < 2) {
            throw new IllegalArgumentException("2명 이상부터 5명 이하까지 등록 가능합니다.");
        }

        return names;
    }
}
