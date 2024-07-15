package com.masmovil.developers.core.domain.repository;

import com.masmovil.developers.core.domain.model.Developer.Developer;
import com.masmovil.developers.core.domain.model.Developer.DeveloperName;

import java.util.List;
import java.util.Optional;

public interface Repository {
    List<Developer> getAllDevelopers();
    Optional<Developer> getDeveloperByName(String name);
    List<Developer> getDevelopersByQuery(String name, String skill);
}
