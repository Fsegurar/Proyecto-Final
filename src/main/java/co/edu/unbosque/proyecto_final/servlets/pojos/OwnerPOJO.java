package edu.unbosque.Taller_5.servlets.pojos;

public class OwnerPOJO {

    private String username;
    private Integer person_id;
    private String name;
    private String address;
    private String neighborhood;
    private PetPOJO pet;

    public OwnerPOJO() {}

    public OwnerPOJO(String username, Integer person_id, String name, String address, String neighborhood) {
        this.username = username;
        this.person_id = person_id;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public OwnerPOJO(String username, Integer person_id, String name, String address, String neighborhood, PetPOJO pet) {
        this.username = username;
        this.person_id = person_id;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
        this.pet = pet;
    }

    public OwnerPOJO( String name, String address, String neighborhood, PetPOJO pet) {
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
        this.pet=pet;
    }

    public OwnerPOJO(String name, String address, String neighborhood) {
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public PetPOJO getPet() {
        return pet;
    }

    public void setPet(PetPOJO pet) {
        this.pet = pet;
    }
}
