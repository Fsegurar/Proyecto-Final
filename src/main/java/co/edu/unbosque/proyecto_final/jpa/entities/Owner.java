package co.edu.unbosque.proyecto_final.jpa.entities;


import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Owner")
@NamedQueries({
        @NamedQuery(name = "Owner.findByUserName",
                query = "SELECT a FROM Owner a JOIN FETCH a.userapp u WHERE u.username = :username")
})
public class Owner implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username",referencedColumnName = "username")
    private UserApp userapp;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, name = "person_id")
    private Integer person_id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "neighborhood")
    private String neighborhood;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();

    @PreUpdate
    private void onUpdate(){
        pets=null;
    }

    public Owner() {}

    public Owner( String name, String address, String neighborhood) {
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }
    /*public Owner( String username,Integer person_id,String name, String address, String neighborhood) {
        this.usename=username;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
        this.person_id=person_id;
    }*/


    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public UserApp getUserapp() {
        return userapp;
    }

    public void setUserapp(UserApp userapp) {
        this.userapp = userapp;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setOwner(this);
    }
}