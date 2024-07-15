package com.masmovil.developers.infrastructure;

import com.masmovil.developers.core.application.getdeveloper.GetDeveloperRequest;
import com.masmovil.developers.core.application.getdeveloper.GetDeveloperResponse;
import com.masmovil.developers.core.application.getdevelopers.GetDevelopersRequest;
import com.masmovil.developers.core.application.getdevelopers.GetDevelopersResponse;
import com.masmovil.developers.core.application.getdevelopers.GetDevelopersUseCase;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava3.core.AbstractVerticle;
import io.vertx.rxjava3.ext.web.Router;
import io.vertx.rxjava3.ext.web.RoutingContext;
import io.vertx.rxjava3.ext.web.handler.BodyHandler;
import com.masmovil.developers.core.application.getdeveloper.GetDeveloperUseCase;

public class DeveloperVerticle extends AbstractVerticle {

    private final GetDeveloperUseCase getDeveloperUseCase;
    private final GetDevelopersUseCase getDevelopersUseCase;

    public DeveloperVerticle(GetDeveloperUseCase getDeveloperUseCase, GetDevelopersUseCase getDevelopersUseCase) {
        this.getDeveloperUseCase = getDeveloperUseCase;
        this.getDevelopersUseCase = getDevelopersUseCase;
    }

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.get("/developer/:name").handler(this::handleGetDeveloper);
        router.get("/developer").handler(this::handleGetDevelopers);

        vertx
                .createHttpServer()
                .requestHandler(router)
                .rxListen(8080)
                .subscribe(
                        result -> System.out.println("Server started on port 8080"),
                        error -> System.out.println("Failed to start server: " + error.getCause()));
    }

    private void handleGetDeveloper(RoutingContext routingContext) {
        String name = routingContext.pathParam("name");
        GetDeveloperRequest request = new GetDeveloperRequest(name);
        GetDeveloperResponse response = getDeveloperUseCase.execute(request);

        if (response.getDeveloper() != null) {
            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(response.getDeveloper().encodePrettily());
        } else {
            routingContext.response()
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json")
                    .end(new JsonObject().put("error", response.getError()).encodePrettily());
        }
    }

    private void handleGetDevelopers(RoutingContext routingContext) {
        String name = routingContext.queryParams().get("name");
        String skill = routingContext.queryParams().get("skill");

        GetDevelopersRequest request = new GetDevelopersRequest(name, skill);
        GetDevelopersResponse response = getDevelopersUseCase.execute(request);

        if (response.getDevelopers() != null) {
            routingContext.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json")
                    .end(response.getDevelopers().encodePrettily());
        } else {
            routingContext.response()
                    .setStatusCode(404)
                    .putHeader("content-type", "application/json")
                    .end(new JsonObject().put("error", response.getError()).encodePrettily());
        }
    }
}
