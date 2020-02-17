package sabouni.margaux.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contact {

    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("linkedin")
    @Expose
    private String linkedin;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("nationalities")
    @Expose
    private List<Nationality> nationalities = null;

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLinkedin() {
            return linkedin;
        }

        public void setLinkedin(String linkedin) {
            this.linkedin = linkedin;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public List<Nationality> getNationalities() {
            return nationalities;
        }

        public void setNationalities(List<Nationality> nationalities) {
            this.nationalities = nationalities;
        }

    }
