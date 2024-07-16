package com.masmovil.developers.core.domain.repository;

import com.masmovil.developers.core.domain.model.developer.Developer;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface DeveloperRepository {
    Single<List<Developer>> getAll();
    Maybe<Developer> getByName(String name);
    Single<List<Developer>> getByQuery(String name, String skill);
}
