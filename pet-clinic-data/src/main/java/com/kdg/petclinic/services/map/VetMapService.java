package com.kdg.petclinic.services.map;

import com.kdg.petclinic.model.Vet;
import com.kdg.petclinic.services.SpecialityService;
import com.kdg.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        if (object != null) {
            object.getSpecialties().forEach(specialty -> {
                if (specialty.getId() == null) {
                    specialty.setId(specialityService.save(specialty).getId());
                }
            });
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
