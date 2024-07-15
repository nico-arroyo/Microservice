package com.masmovil.developers.infrastructure;

import com.masmovil.developers.core.application.getdeveloper.GetDeveloperUseCase;
import com.masmovil.developers.core.application.getdevelopers.GetDevelopersUseCase;
import com.masmovil.developers.core.domain.repository.FileRepository;
import com.masmovil.developers.core.domain.repository.Repository;
import io.vertx.rxjava3.core.Vertx;

public class MainApplication {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        Repository repository = new FileRepository(vertx);
        GetDeveloperUseCase getDeveloperUseCase = new GetDeveloperUseCase(repository);
        GetDevelopersUseCase getDevelopersUseCase = new GetDevelopersUseCase(repository);

        DeveloperVerticle developerVerticle = new DeveloperVerticle(getDeveloperUseCase, getDevelopersUseCase);
        vertx.deployVerticle(developerVerticle);
    }
}

