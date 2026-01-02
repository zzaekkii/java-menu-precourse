package menu.domain.coach;

import java.util.List;

public class Coach {
    private final String name;
    private final List<String> dislikeFoods;

    public Coach(String name, List<String> dislikeFoods) {
        this.name = name;
        this.dislikeFoods = dislikeFoods;
    }

}
