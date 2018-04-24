package com.example.giansantos.androidphp;

public class Messages {
    private String mbody;
    private String musername;
    private String mdate;
    public Messages(String body, String username, String date){
        mbody = body;
        musername = username;
        mdate = date;
    }

    public String getMbody() {
        return mbody;
    }

    public String getMdate() {
        return mdate;
    }

    public String getMusername() {
        return musername;
    }

    public void setMbody(String mbody) {
        this.mbody = mbody;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public void setMusername(String musername) {
        this.musername = musername;
    }

}