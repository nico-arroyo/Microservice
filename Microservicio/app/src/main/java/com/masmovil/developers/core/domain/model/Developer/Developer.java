package com.masmovil.developers.core.domain.model.developer;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class Developer {

    private final DeveloperId id;
    private final DeveloperName name;
    private final List<String> skills;

    private Developer(Builder builder) throws IllegalArgumentException {
        if (builder.skills == null || builder.skills.isEmpty()) {
            throw new IllegalArgumentException("Skills cannot be null or empty");
        }

        id = builder.id;
        name = builder.name;
        skills = builder.skills;
    }

    public static Developer create(JsonObject data) {
        return builder()
                .withId(DeveloperId.of(data.getString("id")))
                .withName(DeveloperName.fromJson(data.getJsonObject("name")))
                .withSkills(data.getJsonArray("skills").getList())
                .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public JsonObject toJson() {
        return new JsonObject()
                .put("id", id.toString())
                .put("name", name.toJson())
                .put("skills", new JsonArray(skills));
    }

    public static final class Builder {
        private DeveloperId id;
        private DeveloperName name;
        private List<String> skills;

        private Builder() {}

        public Builder withId(DeveloperId id) {
            this.id = id;
            return this;
        }

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

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!this.getClass().equals(o.getClass())) {
            return false;
        }
        Developer other = (Developer) o;
        return this.id.equals(other.id);
    }

    public DeveloperId getId() {
        return id;
    }

    public DeveloperName getName() {
        return name;
    }

    public List<String> getSkills() {
        return skills;
    }
}
