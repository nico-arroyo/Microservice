package com.masmovil.developers.core.application.getdevelopers;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class GetDevelopersResponse {
    private final JsonArray developers;
    private final String error;

    public GetDevelopersResponse(List<JsonObject> developers) {
        this.developers = new JsonArray(developers);
        this.error = null;
    }

    public GetDevelopersResponse(String error) {
        this.developers = null;
        this.error = error;
    }

    public JsonArray getDevelopers() {
        return developers;
    }

    public String getError() {
        return error;
    }
}
