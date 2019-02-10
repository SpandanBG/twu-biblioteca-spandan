package com.biblioteca.utils;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

// Represents a set of options a user can choose
public class Options {

    private final Map<String, Action> actionMap;
    private final List<Pair<String, String>> descriptionMap;
    private String suffix;
    private String prefix;

    public Options() {
        actionMap = new HashMap<>();
        descriptionMap = new ArrayList<>();
    }

    public void addOption(String option, String description, Action action) {
        descriptionMap.add(new Pair<>(option, description));
        actionMap.put(option, action);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    String getPrefix() {
        return prefix;
    }

    String getSuffix() {
        return suffix;
    }

    void select(String option) {
        actionMap.get(option).execute();
    }

    public void selectOrDefault(String option, Action defaultAction) {
        actionMap.getOrDefault(option, defaultAction).execute();
    }

    void forEachOption(Consumer<? super Pair> action) {
        descriptionMap.forEach(action);
    }
}
