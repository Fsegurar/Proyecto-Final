package co.edu.unbosque.proyecto_final.jpa.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Vet")
@NamedQueries({
        @NamedQuery(name = "Vet.findByUserName",
                query = "SELECT a FROM Vet a JOIN FETCH a.userapp u WHERE u.username = :username")
})
public class Vet implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username",referencedColumnName = "username")
    private UserApp userapp;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "neighborhood")
    private String neighborhood;

    @OneToMany(mappedBy = "vet_id")
    private List<Visit> visits= new ArrayList<>();

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

    public UserApp getUserapp() {
        return userapp;
    }

    public void setUserapp(UserApp userapp) {
        this.userapp = userapp;
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