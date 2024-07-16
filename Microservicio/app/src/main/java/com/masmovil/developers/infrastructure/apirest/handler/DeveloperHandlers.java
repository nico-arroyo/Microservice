package com.masmovil.developers.infrastructure.apirest.handler;

import com.masmovil.developers.core.application.getdeveloper.GetDeveloperRequest;
import com.masmovil.developers.core.application.getdeveloper.GetDeveloperResponse;
import com.masmovil.developers.core.application.getdeveloper.GetDeveloperUseCase;
import com.masmovil.developers.core.application.getdevelopers.GetDevelopersRequest;
import com.masmovil.developers.core.application.getdevelopers.GetDevelopersResponse;
import com.masmovil.developers.core.application.getdevelopers.GetDevelopersUseCase;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava3.ext.web.RoutingContext;

public class DeveloperHandlers {
    private final GetDeveloperUseCase getDeveloperUseCase;
    private final GetDevelopersUseCase getDevelopersUseCase;

    public DeveloperHandlers(GetDeveloperUseCase getDeveloperUseCase, GetDevelopersUseCase getDevelopersUseCase) {
        this.getDeveloperUseCase = getDeveloperUseCase;
        this.getDevelopersUseCase = getDevelopersUseCase;
    }

    public void handleGetDeveloper(RoutingContext routingContext) {
        var name = routingContext.pathParam("name");
        GetDeveloperRequest request = new GetDeveloperRequest(name);

        getDeveloperUseCase.execute(request)
                .subscribe(response -> {
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
                }, error -> {
                    routingContext.response()
                            .setStatusCode(500)
                            .putHeader("content-type", "application/json")
                            .end(new JsonObject().put("error", "Internal Server Error").encodePrettily());
                });
    }

    public void handleGetDevelopers(RoutingContext routingContext) {
        String name = routingContext.queryParams().get("name");
        String skill = routingContext.queryParams().get("skill");

        GetDevelopersRequest request = new GetDevelopersRequest(name, skill);

        getDevelopersUseCase.execute(request)
                .subscribe(response -> {
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
                }, error -> {
                    routingContext.response()
                            .setStatusCode(500)
                            .putHeader("content-type", "application/json")
                            .end(new JsonObject().put("error", "Internal Server Error").encodePrettily());
                });

    }
}
