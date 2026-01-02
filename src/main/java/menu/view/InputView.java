package menu.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String SEPARATOR = ",";

    public List<String> readCoachNames() {
        String input = readLine();

        nullCheck(input);

        input = input.trim();

        validateSeparatorPattern(input);

        return separate(input);
    }

    private static String readLine() {
        return Console.readLine();
    }

    private static void nullCheck(String input) {
        if (input == null || input.isBlank()) {
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

    private List<String> separate(String input) {
        String[] tokens = input.split(SEPARATOR);

        List<String> names = new ArrayList<>();

        for (String token: tokens) {
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
