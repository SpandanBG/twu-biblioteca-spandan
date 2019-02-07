package com.biblioteca.userinteface;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ActivityTest {

    @Test
    void expectsOnCreateOnRunningAndOnExitToRunInSequence() {
        final ApplicationIO appIO = mock(ApplicationIO.class);
        Activity activity = new Activity(appIO) {
            @Override
            public void onCreate() {
                appIO.print("onCreate Called");
            }

            @Override
            public void onRunning() {
                appIO.print("onRunning Called");
            }

            @Override
            public void onExit() {
                appIO.print("onExit Called");
            }
        };

        activity.run();

        verify(appIO).print("onCreate Called");
        verify(appIO).print("onRunning Called");
        verify(appIO).print("onExit Called");
    }

}