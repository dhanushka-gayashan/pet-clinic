package com.kdg.petclinic.bootstrap;

import com.kdg.petclinic.model.*;
import com.kdg.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll().size() == 0) {
            loadData();
        }
    }

    private void loadData() {

        PetType dog = new PetType();
        dog.setName("Dog");
        dog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        cat = petTypeService.save(cat);

        System.out.println("Loaded Pet Types..........");


        Specialty radiology = new Specialty();
        radiology.setDescription("radiology");
        radiology = specialityService.save(radiology);

        Specialty surgery = new Specialty();
        radiology.setDescription("surgery");
        surgery = specialityService.save(surgery);

        Specialty dentistry = new Specialty();
        radiology.setDescription("dentistry");
        dentistry = specialityService.save(dentistry);

        System.out.println("Loaded Specialty..........");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Westgate");
        owner1.setCity("Henderson");
        owner1.setTelephone("123456789");

        Pet mikesPet = new Pet();
        mikesPet.setName("Rosco");
        mikesPet.setPetType(dog);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(mikesPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Sea Road");
        owner2.setCity("Tukapuna");
        owner2.setTelephone("987654321");

        Pet fionaPet = new Pet();
        fionaPet.setName("Julia");
        fionaPet.setPetType(cat);
        fionaPet.setOwner(owner2);
        fionaPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(fionaPet);
        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(fionaPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        System.out.println("Loaded Owners..........");


        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(radiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(surgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets...........");
    }
}
