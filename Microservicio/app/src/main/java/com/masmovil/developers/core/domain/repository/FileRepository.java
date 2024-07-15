package com.masmovil.developers.core.domain.repository;

import com.masmovil.developers.core.domain.model.Developer.Developer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava3.core.Vertx;
import io.vertx.rxjava3.core.buffer.Buffer;
import io.vertx.rxjava3.core.file.FileSystem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileRepository implements Repository {
    private static final String FILE_PATH = "src/main/resources/developers.json"; // POR EJEMPLO
    private final Vertx vertx;

    public FileRepository(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public List<Developer> getAllDevelopers() {
        FileSystem fileSystem = vertx.fileSystem();
        Buffer buffer = fileSystem.readFileBlocking(FILE_PATH);
        JsonArray jsonArray = new JsonArray(String.valueOf(buffer));
        return jsonArray.stream()
                .map(obj -> (JsonObject) obj)
                .map(data -> Developer.create(data))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Developer> getDeveloperByName(String name) {
        return getAllDevelopers().stream()
                .filter(developer -> developer.toJson()
                        .getJsonObject("name")
                        .getString("first_name")
                        .concat(" ")
                        .concat(developer.toJson().getJsonObject("name").getString("last_name"))
                        .equalsIgnoreCase(name))
                .findFirst();
    }


    @Override
    public List<Developer> getDevelopersByQuery(String name, String skill) {
        return getAllDevelopers().stream()
                .filter(developer -> (name == null || developer.toJson()
                        .getJsonObject("name")
                        .getString("first_name")
                        .concat(" ")
                        .concat(developer.toJson().getJsonObject("name").getString("last_name"))
                        .equalsIgnoreCase(name)) &&
                        (skill == null || developer.toJson().getJsonArray("skills").contains(skill)))
                .collect(Collectors.toList());
    }
}
