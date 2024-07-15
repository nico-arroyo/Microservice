package com.masmovil.developers.core.domain.model.Developer;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class Developer {

    private final DeveloperName name;
    private final List<String> skills;

    private Developer(Builder builder) {
        name = builder.name;
        skills = builder.skills;
    }

    public static Developer create(JsonObject data) {
        return builder()
                .withName(DeveloperName.fromJson(data.getJsonObject("name")))
                .withSkills(data.getJsonArray("skills").getList())
                .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public JsonObject toJson() {
        return new JsonObject()
                .put("name", name.toJson())
                .put("skills", new JsonArray(skills));
    }

    public static final class Builder {
        private DeveloperName name;
        private List<String> skills;

        private Builder() {}

        public Builder withName(DeveloperName name) {
            this.name = name;
            return this;
        }

        public Builder withSkills(List<String> skills) {
            this.skills = skills;
            return this;
        }

        public Developer build() {
            return new Developer(this);
        }
    }
}
