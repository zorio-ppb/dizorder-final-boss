package com.zorio.ppb.marketupdatescore;

public class Selection {

    private String id;
    private String name;
    private Float odd;

    public Selection() {}
    public Selection(String id, String name, Float odd) {
        this.id = id;
        this.name = name;
        this.odd = odd;
    }

    public Float getOdd() {
        return odd;
    }

    public void setOdd(Float odd) {
        this.odd = odd;
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

    @Override
    public String toString() { return String.format("Selection [id=%s, name=%s, odd=%s]", id, name, odd); }

}
