package com.biblioteca.userinteface;

// Represents a sequence of stages of a phase of application
abstract public class ActivityLifecycle {

    public final ApplicationIO appIO;

    public ActivityLifecycle(ApplicationIO appIO) {
        this.appIO = appIO;
    }

    public void run() {
        onStart();
        onRunning();
        onExit();
    }

    abstract public void onStart();
    abstract public void onRunning();
    abstract public void onExit();
}
