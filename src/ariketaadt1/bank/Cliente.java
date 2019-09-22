/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ariketaadt1.bank;

import java.util.Objects;

/**
 *
 * @author rvalv
 */
public class Cliente {
    private Long id=null;
    private String city;
    private String email;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private long phone;
    private String state;
    private String street;
    private int zip;

    public Cliente(String city, String email, String firstName, String lastName, String middleInitial, long phone, String state, String street, int zip) {
        this.city = city;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.phone = phone;
        this.state = state;
        this.street = street;
        this.zip = zip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "cliente{" + "id=" + id + ", city=" + city + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", middleInitial=" + middleInitial + ", phone=" + phone + ", state=" + state + ", street=" + street + ", zip=" + zip + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.city);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.firstName);
        hash = 53 * hash + Objects.hashCode(this.lastName);
        hash = 53 * hash + Objects.hashCode(this.middleInitial);
        hash = 53 * hash + Objects.hashCode(this.state);
        hash = 53 * hash + Objects.hashCode(this.street);
        hash = 53 * hash + this.zip;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.phone != other.phone) {
            return false;
        }
        if (this.zip != other.zip) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.middleInitial, other.middleInitial)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
