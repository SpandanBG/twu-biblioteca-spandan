package com.biblioteca.userinteface;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

// Represents business policy to communicate with application
public class ApplicationIO {

    private final BufferedWriter writer;

    private ApplicationIO(BufferedWriter writer) {
        this.writer = writer;
    }

    public static ApplicationIO createConsoleIO() {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        return new ApplicationIO(writer);
    }

    public void print(String message) {
        try {
            writer.write(message);
            writer.flush();
        } catch (IOException ignored) {
            throw new NoOutputDeviceException();
        }
    }
}
