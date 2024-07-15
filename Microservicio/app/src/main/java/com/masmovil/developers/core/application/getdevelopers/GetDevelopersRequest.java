package com.masmovil.developers.core.application.getdevelopers;

import java.util.List;

public class GetDevelopersRequest {
    private final String name;
    private final String skill;

    public GetDevelopersRequest(String name, String skill) {
        this.name = name;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public String getSkill() {
        return skill;
    }
}
