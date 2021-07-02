package com.example.e_voting.Model;

public class Winers {

    private String name;
    private Integer count;
    private String id;

    public Winers(String name, Integer count, String id) {
        this.name = name;
        this.count = count;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Winers() {
    }
}
