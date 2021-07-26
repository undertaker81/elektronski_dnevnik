package com.iktpreobuka.elektronskidnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronskidnevnik.entities.Predmet;

public interface PredmetRepository extends CrudRepository<Predmet, Integer> {

}
