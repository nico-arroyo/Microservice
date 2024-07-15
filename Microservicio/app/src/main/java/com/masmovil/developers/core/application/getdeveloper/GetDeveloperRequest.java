package com.masmovil.developers.core.application.getdeveloper;

public class GetDeveloperRequest {

    private final String name;

    public GetDeveloperRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
