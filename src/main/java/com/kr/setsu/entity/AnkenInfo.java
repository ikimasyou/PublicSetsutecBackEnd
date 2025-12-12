package com.kr.setsu.entity;

public class AnkenInfo {
    private String ankenName;

    public void setAnkenName(String ankenName) {
        this.ankenName = ankenName;
    }

    @Override
    public String toString() {
        return "AnkenInfo{" +
                "ankenName=" + ankenName +
                '}';
    }

    public String getAnkenName() {
        return ankenName;
    }
    public String ankenNotFoundError(){
        return "Anken not found";
    }
}
