package com.masmovil.developers.infrastructure;

import com.masmovil.developers.core.application.getdeveloper.GetDeveloperUseCase;
import com.masmovil.developers.core.application.getdevelopers.GetDevelopersUseCase;
import com.masmovil.developers.core.domain.repository.DeveloperRepository;
import com.masmovil.developers.infrastructure.apirest.DeveloperServer;
import com.masmovil.developers.infrastructure.apirest.handler.DeveloperHandlers;
import com.masmovil.developers.infrastructure.devfile.DeveloperFileRepository;
import io.vertx.rxjava3.core.Vertx;

public class MainApplication {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DeveloperRepository repository = new DeveloperFileRepository(vertx);
        GetDeveloperUseCase getDeveloperUseCase = new GetDeveloperUseCase(repository);
        GetDevelopersUseCase getDevelopersUseCase = new GetDevelopersUseCase(repository);

        DeveloperHandlers handlers = new DeveloperHandlers(getDeveloperUseCase, getDevelopersUseCase);
        DeveloperServer developerServer = new DeveloperServer(handlers);
        vertx.deployVerticle(developerServer);
    }
}

