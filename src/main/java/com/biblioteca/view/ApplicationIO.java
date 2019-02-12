package com.biblioteca.view;

import com.biblioteca.view.exceptions.NoInputDeviceException;
import com.biblioteca.view.exceptions.NoOutputDeviceException;

import java.io.*;

// Represents business policy to communicate with application
public class ApplicationIO {

    private final BufferedWriter writer;
    private final BufferedReader reader;

    private ApplicationIO(BufferedWriter writer, BufferedReader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    public static ApplicationIO createConsoleIO() {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return new ApplicationIO(writer, reader);
    }

    public void print(String message) {
        try {
            writer.write(message);
            writer.flush();
        } catch (IOException ignored) {
            throw new NoOutputDeviceException();
        }
    }

    public String read() {
        try {
            return reader.readLine();
        } catch (IOException ignored) {
            throw new NoInputDeviceException();
        }
    }
}
