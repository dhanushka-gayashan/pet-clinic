package com.kdg.petclinic.services.springdatajpa;

import com.kdg.petclinic.model.Owner;
import com.kdg.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OwnerSpringDataJpaServiceTest {

    final String LAST_NAME = "Smith";

    @Mock
    OwnerRepository repository;

    @InjectMocks
    OwnerSpringDataJpaService service;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        Mockito.when(repository.findByLastName(ArgumentMatchers.any())).thenReturn(returnOwner);

        Owner smith = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());

        Mockito.verify(repository, Mockito.times(1)).findByLastName(ArgumentMatchers.any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(Owner.builder().id(1L).build());
        returnOwnersSet.add(Owner.builder().id(2L).build());

        Mockito.when(repository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        assertNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();

        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(returnOwner);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);

        Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        Mockito.verify(repository, Mockito.times(1)).delete(ArgumentMatchers.any());
    }

    @Test
    void deleteById() {
        service.deleteById(returnOwner.getId());

        Mockito.verify(repository, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
    }
}