package com.biblioteca.userinteface;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ActivityLifecycleTest {

    @Test
    void expectsOnCreateOnRunningAndOnExitToRunInSequence() {
        final ApplicationIO appIO = mock(ApplicationIO.class);
        ActivityLifecycle activity = new ActivityLifecycle(appIO) {
            @Override
            public void onStart() {
                appIO.print("onStart Called");
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

        verify(appIO).print("onStart Called");
        verify(appIO).print("onRunning Called");
        verify(appIO).print("onExit Called");
    }

}