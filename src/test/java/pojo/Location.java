package pojo;

import java.util.List;

public class Location {
    private int id;
    private String city;
    private String country;
    private List<Address> address;

    Location() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public List<Address> getAddress() {
        return address;
    }
}
