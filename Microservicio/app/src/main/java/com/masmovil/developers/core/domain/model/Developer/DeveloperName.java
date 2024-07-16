package com.masmovil.developers.core.domain.model.developer;

import io.vertx.core.json.JsonObject;

public class DeveloperName {

    private final String firstName;
    private final String lastName;

    private DeveloperName(String firstName, String lastName) throws IllegalArgumentException {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static DeveloperName of(String name, String lastName) throws IllegalArgumentException {
        return new DeveloperName(name, lastName);
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

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!this.getClass().equals(o.getClass())) {
            return false;
        }
        DeveloperName other = (DeveloperName) o;
        return this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
