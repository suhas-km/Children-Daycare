package com.neu.csye6200.daycare.model;

public class Person {
    private String id;
    private String name;
    private String email;
    private int age;

    public Person() {
    }

    public Person(String id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person Details: " +
                "\nID=" + id +
                "\n, Name=" + name +
                "\n, Email=" + email +
                "\n, Age=" + age;
    }
}
