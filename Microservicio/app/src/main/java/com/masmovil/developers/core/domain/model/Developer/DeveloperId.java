package com.masmovil.developers.core.domain.model.developer;

import java.util.UUID;

public class DeveloperId {
    private final UUID id;

    public DeveloperId(UUID id) throws IllegalArgumentException {
        if (id == null || id.toString().isEmpty()) {
            throw new IllegalArgumentException("Id cannot be null or empty");
        }
        this.id = id;    }

    public static DeveloperId create(){
        return new DeveloperId(UUID.randomUUID());
    }

    public static DeveloperId of(String id){
        return new DeveloperId(UUID.fromString(id));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!this.getClass().equals(o.getClass())) {
            return false;
        }
        DeveloperId other = (DeveloperId) o;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public UUID getId() {
        return id;
    }
}
