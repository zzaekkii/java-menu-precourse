package menu.domain;

import java.util.List;
import java.util.Map;

public class Week {
    private List<Day> days;
    private Map<Category, Integer> categories;

    public void addDay(Day day) {
        days.add(day);

        if (categories.containsKey(day.category())) {
            categories.put(day.category(), categories.get(day.category()) + 1);
        }

        categories.put(day.category(), 1);
    }

    public boolean canAdd(Category category) {
        return categories.get(category) < 2;
    }
}
