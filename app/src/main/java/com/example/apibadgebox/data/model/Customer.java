package com.example.apibadgebox.data.model;

public class Customer {
    private int id, number, zipcode;
    private String name, address, city, country, email_cert, sdi, fiscal_code, email, phone, fax, note;

    public Customer( int number, int zipcode, String name, String address, String city, String country, String email_cert, String sdi, String fiscal_code, String email, String phone, String fax, String note) {
        this.number = number;
        this.zipcode = zipcode;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.email_cert = email_cert;
        this.sdi = sdi;
        this.fiscal_code = fiscal_code;
        this.email = email;
        this.phone = phone;
        this.fax = fax;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail_cert() {
        return email_cert;
    }

    public void setEmail_cert(String email_cert) {
        this.email_cert = email_cert;
    }

    public String getSdi() {
        return sdi;
    }

    public void setSdi(String sdi) {
        this.sdi = sdi;
    }

    public String getFiscal_code() {
        return fiscal_code;
    }

    public void setFiscal_code(String fiscal_code) {
        this.fiscal_code = fiscal_code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
