package com.example.periodtracker.model;

public class Mucus {
    private int custom;
    private String customname;
    private int hidden;
    private int id;
    private int idsmucus;
    private String imagename;
    private String name;
    private int nused;
    private int uid;

    public Mucus(int id2, int idsmucus2, int uid2, String name2, String customname2, String imagename2, int nused2, int hidden2, int custom2) {
        this.id = id2;
        this.idsmucus = idsmucus2;
        this.uid = uid2;
        this.name = name2;
        this.customname = customname2;
        this.imagename = imagename2;
        this.nused = nused2;
        this.hidden = hidden2;
        this.custom = custom2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id2) {
        this.id = id2;
    }

    public int getIdMucus() {
        return this.idsmucus;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid2) {
        this.uid = uid2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getCustomName() {
        return this.customname;
    }

    public String getImageName() {
        return this.imagename;
    }

    public int getNused() {
        return this.nused;
    }

    public int getHidden() {
        return this.hidden;
    }

    public void setHidden(int hidden2) {
        this.hidden = hidden2;
    }

    public int getCustom() {
        return this.custom;
    }

    public void setCustom(int custom2) {
        this.custom = custom2;
    }

    public String toString() {
        return "Symptoms [id=" + this.id + ", idsymptoms=" + this.idsmucus + ", uid=" + this.uid + ", name=" + this.name + ", customname=" + this.customname + ", imagename=" + this.imagename + ", nused=" + this.nused + ", hidden=" + this.hidden + ", custom=" + this.custom + "]";
    }
}
