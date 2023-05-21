package com.pemrogramanmobile.firestore.model;

public class Pakaian {
    private String id, namaPakaian, cuciBasah, cuciKering, setrika;

    public Pakaian(){

    }

    public Pakaian(String namaPakaian, String cuciBasah, String cuciKering, String setrika){
        this.namaPakaian = namaPakaian;
        this.cuciBasah = cuciBasah;
        this.cuciKering = cuciKering;
        this.setrika = setrika;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaPakaian() {
        return namaPakaian;
    }

    public void setNamaPakaian(String namaPakaian) {
        this.namaPakaian = namaPakaian;
    }

    public String getCuciBasah() {
        return cuciBasah;
    }

    public void setCuciBasah(String cuciBasah) {
        this.cuciBasah = cuciBasah;
    }

    public String getCuciKering() {
        return cuciKering;
    }

    public void setCuciKering(String cuciKering) {
        this.cuciKering = cuciKering;
    }

    public String getSetrika() {
        return setrika;
    }

    public void setSetrika(String setrika) {
        this.setrika = setrika;
    }
}
