package model;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class Location {
    private int id;
    private String city;
    private String country;
    private List<Address> address;

//    @Data
//    public static class Address {
//        private String street;
//        private String flat_no;
//        private int pincode;
//        private String type;
//    }

}
