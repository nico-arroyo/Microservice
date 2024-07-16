package com.masmovil.developers.core.application.getdevelopers;

import com.masmovil.developers.core.domain.repository.DeveloperRepository;
import io.reactivex.rxjava3.core.Single;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.stream.Collectors;

public class GetDevelopersUseCase {

    private final DeveloperRepository repository;

    public GetDevelopersUseCase(DeveloperRepository repository) {
        this.repository = repository;
    }

    public Single<GetDevelopersResponse> execute(GetDevelopersRequest request) {
        String name = request.getName();
        String skill = request.getSkill();
        return repository.getByQuery(name, skill)
                .map (developers -> {
                    if (developers.isEmpty()) {
                        return new GetDevelopersResponse("No developers found matching the criteria");
                    } else {
                        List<JsonObject> developerJsons = developers.stream()
                                .map(developer -> developer.toJson())
                                .collect(Collectors.toList());
                        return new GetDevelopersResponse(developerJsons);
                    }
                });


    }
}
