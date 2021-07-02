package com.example.e_voting.Model;

public class DeleteReasonModel {
    private String name;
    private String SSN;
    private String etDeleteReason;
    private String deleteImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getEtDeleteReason() {
        return etDeleteReason;
    }

    public void setEtDeleteReason(String etDeleteReason) {
        this.etDeleteReason = etDeleteReason;
    }

    public String getDeleteImage() {
        return deleteImage;
    }

    public void setDeleteImage(String deleteImage) {
        this.deleteImage = deleteImage;
    }

    public DeleteReasonModel() {
    }

    public DeleteReasonModel(String name, String SSN, String etDeleteReason, String deleteImage) {
        this.name = name;
        this.SSN = SSN;
        this.etDeleteReason = etDeleteReason;
        this.deleteImage = deleteImage;
    }
}
