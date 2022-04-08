package com.sachi.okhttp;

public class TicketTypeDataClass {
    int id;
    String tktType;
    int amt;

    public TicketTypeDataClass(int id, String tktType, int amt) {
        this.id = id;
        this.tktType = tktType;
        this.amt = amt;
    }

    @Override
    public String toString() {
        return tktType;
    }
}
