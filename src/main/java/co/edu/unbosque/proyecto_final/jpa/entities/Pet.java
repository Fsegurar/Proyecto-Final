package edu.unbosque.Taller_5.jpa.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Pet")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Integer pet_id;

    @Column(name = "microchip", unique = true)
    private String microchip;

    @Column(name = "name")
    private String name;

    @Column(name = "species")
    private String species;

    @Column(name = "race")
    private String race;

    @Column(name = "size")
    private String size;

    @Column(name = "sex")
    private String sex;

    @Column(name = "picture")
    private String picture;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "person_id", unique = true)
    private Owner owner;

    @OneToMany(mappedBy = "pet", fetch = FetchType.EAGER)
    private List<Visit> visits;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.MERGE)
    private List<PetCase> cases = new ArrayList<>();

    @PreUpdate
    private void onUpdate(){
        owner.setPets(null);
    }


    public Pet(String microchip, String name, String species,String sex, String race, String size, String picture) {
        this.microchip = microchip;
        this.name = name;
        this.species = species;
        this.sex=sex;
        this.race = race;
        this.size = size;
        this.picture = picture;
    }

    public Pet(){}

    public void addPetCase(PetCase petcase) {
        cases.add(petcase);
        petcase.setPet(this);
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
        visit.setPet(this);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public List<PetCase> getCases() {
        return cases;
    }

    public void setCases(List<PetCase> cases) {
        this.cases = cases;
    }

    public Integer getPet_id() {
        return pet_id;
    }

    public void setPet_id(Integer pet_id) {
        this.pet_id = pet_id;
    }

    public String getMicrochip() {
        return microchip;
    }

    public void setMicrochip(String microchip) {
        this.microchip = microchip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}

