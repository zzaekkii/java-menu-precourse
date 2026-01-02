package menu.domain;

public enum DayOfWeek {
    MON("월요일"),
    TUE("화요일"),
    WED("수요일"),
    THU("목요일"),
    FRI("금요일"),
    ;

    public static final DayOfWeek[] DAYS_OF_WEEK = {MON, TUE, WED, THU, FRI};
    private final String description;

    DayOfWeek(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
