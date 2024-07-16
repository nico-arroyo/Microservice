package com.masmovil.developers.infrastructure.devfile;

import com.masmovil.developers.core.domain.model.developer.Developer;
import com.masmovil.developers.core.domain.repository.DeveloperRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava3.core.Vertx;
import io.vertx.rxjava3.core.buffer.Buffer;
import io.vertx.rxjava3.core.file.FileSystem;

import java.util.List;
import java.util.stream.Collectors;

public class DeveloperFileRepository implements DeveloperRepository {

    private static final String FILE_PATH = "src/main/resources/developers.json"; // FOR EXAMPLE
    private final Vertx vertx;

    public DeveloperFileRepository(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public Single<List<Developer>> getAll() {
        FileSystem fileSystem = vertx.fileSystem();
        return fileSystem.rxReadFile(FILE_PATH)
                .map(Buffer::toString)
                .map(JsonArray::new)
                .map(jsonArray -> jsonArray.stream()
                        .map(obj -> (JsonObject) obj)
                        .map(Developer::create)
                        .collect(Collectors.toList()));
    }

    @Override
    public Maybe<Developer> getByName(String name) {
        return getAll()
                .flattenAsObservable(developers -> developers)
                .filter(developer -> developer.toJson()
                        .getJsonObject("name")
                        .getString("first_name")
                        .concat(" ")
                        .concat(developer.toJson().getJsonObject("name").getString("last_name"))
                        .equalsIgnoreCase(name))
                .firstElement();
    }


    @Override
    public Single<List<Developer>> getByQuery(String name, String skill) {
        return getAll()
                .map(developers -> developers.stream()
                        .filter(developer -> (name == null || developer.toJson()
                                .getJsonObject("name")
                                .getString("first_name")
                                .concat(" ")
                                .concat(developer.toJson().getJsonObject("name").getString("last_name"))
                                .equalsIgnoreCase(name)) &&
                                (skill == null || developer.toJson().getJsonArray("skills").contains(skill)))
                        .collect(Collectors.toList()));
    }
}
