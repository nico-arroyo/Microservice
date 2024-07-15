package com.masmovil.developers.core.application.getdeveloper;

import com.masmovil.developers.core.domain.repository.Repository;

public class GetDeveloperUseCase {

    private final Repository repository;

    public GetDeveloperUseCase(Repository repository) {
        this.repository = repository;
    }

    public GetDeveloperResponse execute(GetDeveloperRequest request) {
        String name = request.getName();
        return repository.getDeveloperByName(name)
                .map(developer -> new GetDeveloperResponse(developer.toJson()))
                .orElseGet(() -> new GetDeveloperResponse("Developer not found"));
    }
}
