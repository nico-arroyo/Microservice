package com.masmovil.developers.core.application.getdeveloper;

import io.vertx.core.json.JsonObject;

public class GetDeveloperResponse {
    private final JsonObject developer;
    private final String error;

    public GetDeveloperResponse(JsonObject developer) {
        this.developer = developer;
        this.error = null;
    }

    public GetDeveloperResponse(String error) {
        this.developer = null;
        this.error = error;
    }

    public JsonObject getDeveloper() {
        return developer;
    }

    public String getError() {
        return error;
    }
}
