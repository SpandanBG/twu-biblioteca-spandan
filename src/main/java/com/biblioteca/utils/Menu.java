package com.biblioteca.utils;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

// Represents a set of options a user can choose
public class Menu {

    private final Map<String, Action> menuActions;
    private final Map<String, String> menuOptions;

    public Menu() {
        menuActions = new HashMap<>();
        menuOptions = new HashMap<>();
    }

    public void addOption(String option, String description, Action action) {
        menuOptions.put(option, description);
        menuActions.put(option, action);
    }

    public void select(String option) {
        menuActions.get(option).execute();
    }

    public void selectOrDefault(String option, Action defaultAction) {
        menuActions.getOrDefault(option, defaultAction).execute();
    }

    public void forEachOption(Consumer<? super Pair> action) {
        menuOptions.forEach((option, description) -> action.accept(new Pair<>(option, description)));
    }
}
