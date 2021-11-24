package edu.unbosque.Taller_5.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "Userapp")

public class UserApp {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "username", cascade = CascadeType.ALL)
    private Official official;

    @OneToOne(mappedBy = "username", cascade = CascadeType.ALL)
    private Owner owner;

    @OneToOne(mappedBy = "username", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Vet vet;

    @PreUpdate
    private void onUpdate(){
        if(owner!=null){
            owner.setPets(null);
        }
        if(vet!=null){
            vet.setVisits(null);
        }

    }


    public UserApp(){}

    public UserApp(String username, String password, String email, String role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Official getOfficial() {
        return official;
    }

    public void setOfficial(Official official) {
        this.official = official;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }
}
