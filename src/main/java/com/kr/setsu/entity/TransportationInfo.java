package com.kr.setsu.entity;

import lombok.Data;

public class TransportationInfo {

	private String date;
    private String category;
    private String tripType;
    private String startStation;
    private String endStation;
    private Integer fare;
    private String note;
    
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	public Integer getFare() {
		return fare;
	}

	public void setFare(Integer fare) {
		this.fare = fare;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "TransportationInfo [date=" + date + ", category=" + category + ", tripType=" + tripType
				+ ", startStation=" + startStation + ", endStation=" + endStation + ", fare=" + fare + ", note=" + note
				+ "]";
	}
    
}
