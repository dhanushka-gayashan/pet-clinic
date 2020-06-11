package com.kdg.petclinic.repositories;

import com.kdg.petclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<Specialty, Long> {
}
