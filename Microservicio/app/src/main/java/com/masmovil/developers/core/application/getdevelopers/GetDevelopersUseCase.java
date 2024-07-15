package com.masmovil.developers.core.application.getdevelopers;

import com.masmovil.developers.core.domain.model.Developer.Developer;
import com.masmovil.developers.core.domain.repository.Repository;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.stream.Collectors;

public class GetDevelopersUseCase {

    private final Repository repository;

    public GetDevelopersUseCase(Repository repository) {
        this.repository = repository;
    }

    public GetDevelopersResponse execute(GetDevelopersRequest request) {
        String name = request.getName();
        String skill = request.getSkill();
        List<Developer> developers = repository.getDevelopersByQuery(name, skill);
        if (developers.isEmpty()) {
            return new GetDevelopersResponse("No developers found matching the criteria");
        } else {
            List<JsonObject> developerJsons = developers.stream()
                    .map(developer -> developer.toJson())
                    .collect(Collectors.toList());
            return new GetDevelopersResponse(developerJsons);
        }
    }
}
