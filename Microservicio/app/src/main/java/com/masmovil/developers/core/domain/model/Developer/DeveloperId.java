package com.masmovil.developers.core.domain.model.Developer;

import java.util.UUID;

public class DeveloperId {
    private final UUID id;

    private DeveloperId(UUID id) {
        this.id = id;
    }

    public static DeveloperId create(){
        return new DeveloperId(UUID.randomUUID());
    }

    public static DeveloperId of(String id){
        return new DeveloperId(UUID.fromString(id));
    }

    public UUID getId() {
        return id;
    }
}
