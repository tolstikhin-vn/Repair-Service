package ru.tolstikhin.entity;

import java.util.List;

public class ServiceCenter {
    private int id;
    private String address;
    private List<String> phoneNumbers;
    private List<CenterMetroLink> centerMetroLinks;

    public ServiceCenter() {
    }

    public ServiceCenter(int id, String address, List<String> phoneNumbers, List<CenterMetroLink> centerMetroLinks) {
        this.id = id;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
        this.centerMetroLinks = centerMetroLinks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<CenterMetroLink> getCenterMetroLinks() {
        return centerMetroLinks;
    }

    public void setCenterMetroLinks(List<CenterMetroLink> centerMetroLinks) {
        this.centerMetroLinks = centerMetroLinks;
    }
}
