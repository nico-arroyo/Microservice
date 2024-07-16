package com.masmovil.developers.infrastructure.apirest;

import com.masmovil.developers.infrastructure.apirest.handler.DeveloperHandlers;
import io.vertx.rxjava3.core.AbstractVerticle;
import io.vertx.rxjava3.ext.web.Router;
import io.vertx.rxjava3.ext.web.handler.BodyHandler;

public class DeveloperServer extends AbstractVerticle {

    private final DeveloperHandlers handlers;

    public DeveloperServer(DeveloperHandlers handlers) {
        this.handlers = handlers;
    }

    @Override
    public void start() {
        Router router = initRouter();
        registerHandlers(router);
        startServer(router);
    }

    private Router initRouter() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        return router;
    }

    private void registerHandlers(Router router) {
        router.get("/developer/:name").handler(handlers::handleGetDeveloper);
        router.get("/developer").handler(handlers::handleGetDevelopers);
    }

    private void startServer(Router router) {
        vertx
                .createHttpServer()
                .requestHandler(router)
                .rxListen(8080)
                .subscribe(
                        result -> System.out.println("Server started on port 8080"),
                        error -> System.out.println("Failed to start server: " + error.getCause()));
    }


}
