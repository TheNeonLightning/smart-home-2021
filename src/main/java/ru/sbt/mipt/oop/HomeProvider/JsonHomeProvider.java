package ru.sbt.mipt.oop.HomeProvider;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHomeProvider implements HomeProvider {

    private final String filename;

    public JsonHomeProvider(String filename) {
        this.filename = filename;
    }

    @Override
    public SmartHome provideHome() {
        Gson gson = new Gson();
        String json = null;

        try {
            json = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            throw new RuntimeException("Could not parse file " + filename, e);
        }

        return gson.fromJson(json, SmartHome.class);
    }
}
