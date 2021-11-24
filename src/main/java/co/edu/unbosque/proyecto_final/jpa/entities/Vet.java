package edu.unbosque.Taller_5.jpa.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Vet")
public class Vet implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "username")
    private UserApp username;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "neighborhood")
    private String neighborhood;

    @OneToMany(mappedBy = "vet", fetch = FetchType.EAGER)
    private List<Visit> visits;

    @PreUpdate
    private void onUpdate(){
        if(visits!=null){
            visits=null;
        }
    }

    public Vet(){}

    public Vet(String name, String address, String neighborhood){
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
        visit.setVet(this);
    }

    public UserApp getUsername() {
        return username;
    }

    public void setUsername(UserApp userapp) {
        this.username = userapp;
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

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

}