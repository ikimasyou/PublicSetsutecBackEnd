package com.kr.setsu.entity;

public class BPPersonnel {

	// 关联社员
    private String userId;

    // 基本信息
    private String surname;
    private String givenName;

    private String surnameKana;
    private String givenNameKana;

    private String surnameEn;
    private String givenNameEn;

    private String gender;
    private String nationality;

    private String contact;
    private String station;

    // 契约信息
    private String company;
    @Override
	public String toString() {
		return "BPPersonnel [userId=" + userId + ", surname=" + surname + ", givenName=" + givenName + ", surnameKana="
				+ surnameKana + ", givenNameKana=" + givenNameKana + ", surnameEn=" + surnameEn + ", givenNameEn="
				+ givenNameEn + ", gender=" + gender + ", nationality=" + nationality + ", contact=" + contact
				+ ", station=" + station + ", company=" + company + ", request=" + request + "]";
	}
	private String request;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getSurnameKana() {
		return surnameKana;
	}
	public void setSurnameKana(String surnameKana) {
		this.surnameKana = surnameKana;
	}
	public String getGivenNameKana() {
		return givenNameKana;
	}
	public void setGivenNameKana(String givenNameKana) {
		this.givenNameKana = givenNameKana;
	}
	public String getSurnameEn() {
		return surnameEn;
	}
	public void setSurnameEn(String surnameEn) {
		this.surnameEn = surnameEn;
	}
	public String getGivenNameEn() {
		return givenNameEn;
	}
	public void setGivenNameEn(String givenNameEn) {
		this.givenNameEn = givenNameEn;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
}
