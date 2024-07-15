package com.masmovil.developers.core.domain.model.Developer;

import io.vertx.core.json.JsonObject;

public class DeveloperName {

    private final String firstName;
    private final String lastName;

    private DeveloperName(String name, String lastName) {
        this.firstName = name;
        this.lastName = lastName;
    }

    public static DeveloperName of(String name, String lastName) throws IllegalArgumentException {
        if (name != null && !name.isEmpty()) {
            return new DeveloperName(name, lastName);
        } else {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public JsonObject toJson() {
        return new JsonObject()
                .put("first_name", firstName)
                .put("last_name", lastName);
    }

    public static DeveloperName fromJson(JsonObject json) {
        return new DeveloperName(
                json.getString("first_name"),
                json.getString("last_name")
        );
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
