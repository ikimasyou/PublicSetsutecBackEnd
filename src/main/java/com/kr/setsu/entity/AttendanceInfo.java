package com.kr.setsu.entity;

public class AttendanceInfo {
	
	private String attendanceId;
	
	private String userId;
		
	private String date;//年月日

	private String division;

	private String begin;

	private String end;

	private String breakday;

	private String breaknight;

	private String workhour;

	private String workcontent;

	private String note;

	private String changework;

	private boolean isAttendance;



	private String kinmuid;

	public String getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getBreakday() {
		return breakday;
	}

	public void setBreakday(String breakday) {
		this.breakday = breakday;
	}

	public String getBreaknight() {
		return breaknight;
	}

	public void setBreaknight(String breaknight) {
		this.breaknight = breaknight;
	}

	public String getWorkhour() {
		return workhour;
	}

	public void setWorkhour(String workhour) {
		this.workhour = workhour;
	}

	public String getWorkcontent() {
		return workcontent;
	}

	public void setWorkcontent(String workcontent) {
		this.workcontent = workcontent;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getChangework() {
		return changework;
	}

	public void setChangework(String changework) {
		this.changework = changework;
	}

	public boolean getIsAttendance() {
		return this.isAttendance;
	}
	public String getKinmuid() {return kinmuid;}

	public void setKinmuid(String kinmuid) {this.kinmuid = kinmuid;}

	@Override
	public String toString() {
		return "AttendanceInfo [attendanceId=" + attendanceId + ", userId=" + userId + ", date=" + date + ", division="
				+ division + ", begin=" + begin + ", end=" + end + ", breakday=" + breakday + ", breaknight="
				+ breaknight + ", workhour=" + workhour + ", workcontent=" + workcontent + ", note=" + note
				+ ", changework=" + changework + ", isAttendance=" + isAttendance + "]";
	}

	public void setIsAttendance(boolean isAttendance) {
		this.isAttendance = isAttendance;
	}
	
	

}
