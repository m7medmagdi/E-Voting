package com.example.e_voting.Model;

import org.json.JSONStringer;

public class Condidate {

    private String name;
    private Integer count;
    private String id;
    private String Talab_Trash7_Image;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTalab_Trash7_Image() {
        return Talab_Trash7_Image;
    }

    public void setTalab_Trash7_Image(String talab_Trash7_Image) {
        Talab_Trash7_Image = talab_Trash7_Image;
    }

    public Condidate() {
    }

    public Condidate(String name, Integer count, String id, String talab_Trash7_Image) {
        this.name = name;
        this.count = count;
        this.id = id;
        Talab_Trash7_Image = talab_Trash7_Image;
    }
}
