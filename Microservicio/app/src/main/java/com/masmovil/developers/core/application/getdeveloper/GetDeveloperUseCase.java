package com.masmovil.developers.core.application.getdeveloper;

import com.masmovil.developers.core.domain.repository.DeveloperRepository;
import io.reactivex.rxjava3.core.Single;

public class GetDeveloperUseCase {

    private final DeveloperRepository repository;

    public GetDeveloperUseCase(DeveloperRepository repository) {
        this.repository = repository;
    }

    public Single<GetDeveloperResponse> execute(GetDeveloperRequest request) {
        String name = request.getName();
        return repository.getByName(name)
                .map(developer -> new GetDeveloperResponse(developer.toJson()))
                .defaultIfEmpty(new GetDeveloperResponse("Developer not found"));
    }
}
