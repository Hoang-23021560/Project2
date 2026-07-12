package com.javaweb.model;

import java.util.List;

public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<String> managerStaffCustomer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getManagerStaffCustomer() {
        return managerStaffCustomer;
    }

    public void setManagerStaffCustomer(List<String> managerStaffCustomer) {
        this.managerStaffCustomer = managerStaffCustomer;
    }
}
