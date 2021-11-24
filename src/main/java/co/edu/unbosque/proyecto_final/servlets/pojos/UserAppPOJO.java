package edu.unbosque.Taller_5.servlets.pojos;

public class UserAppPOJO {

    private String username;
    private String password;
    private String email;
    private String role;
    private OfficialPOJO official;
    private OwnerPOJO owner;
    private VetPOJO vet;


    public UserAppPOJO() {}

    public UserAppPOJO(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UserAppPOJO(String username, String password, String email, String role, OfficialPOJO official) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.official = official;
    }

    public UserAppPOJO(String username, String password, String email, String role, OwnerPOJO owner) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.owner = owner;
    }

    public UserAppPOJO(String username, String password, String email, String role, VetPOJO vet) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.vet = vet;
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

    public OfficialPOJO getOfficial() {
        return official;
    }

    public void setOfficial(OfficialPOJO official) {
        this.official = official;
    }

    public OwnerPOJO getOwner() {
        return owner;
    }

    public void setOwner(OwnerPOJO owner) {
        this.owner = owner;
    }

    public VetPOJO getVet() {
        return vet;
    }

    public void setVet(VetPOJO vet) {
        this.vet = vet;
    }
}
