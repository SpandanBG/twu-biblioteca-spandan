package com.biblioteca.userinteface;

// Represents a sequence of stages of a phase of application
abstract public class Activity {

    protected final ApplicationIO appIO;

    public Activity(ApplicationIO appIO) {
        this.appIO = appIO;
    }

    public void run() {
        onCreate();
        onRunning();
        onExit();
    }

    abstract protected void onCreate();
    abstract protected void onRunning();
    abstract protected void onExit();
}
