package menu.domain.coach;

import menu.domain.DayOfWeek;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Coach {
    private final String name;
    private final List<String> dislikeFoods;
    private final Map<DayOfWeek, String> menus = new HashMap<>();

    public Coach(String name, List<String> dislikeFoods) {
        this.name = name;
        this.dislikeFoods = dislikeFoods;
    }

    public void addMenu(DayOfWeek day, String menu) {
        menus.put(day, menu);
    }

    public String getName() {
        return name;
    }

    public List<String> getDislikeFoods() {
        return dislikeFoods;
    }

    public Map<DayOfWeek, String> getMenus() {
        return new HashMap<>(menus);
    }
}
