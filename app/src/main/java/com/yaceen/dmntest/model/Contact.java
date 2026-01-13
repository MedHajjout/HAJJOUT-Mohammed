package com.yaceen.dmntest.model;

public class Contact {
    private int id;

    public int getId() {
        return id;
    }

    private String name;
    private String phone;

    public Contact(String name,String phone){
        this.name = name;
        this.phone = phone;
    };

    public Contact(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString(){
        return name +" : "+ phone;
    }
}
