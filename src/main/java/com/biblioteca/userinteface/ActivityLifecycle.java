package com.biblioteca.userinteface;

// Represents a sequence of stages of a phase of application
abstract public class ActivityLifecycle {

    public final ApplicationIO appIO;

    public ActivityLifecycle(ApplicationIO appIO) {
        this.appIO = appIO;
    }

    public void run() {
        onCreate();
        onRunning();
        onExit();
    }

    abstract public void onCreate();
    abstract public void onRunning();
    abstract public void onExit();
}
