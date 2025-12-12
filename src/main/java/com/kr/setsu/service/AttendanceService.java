package com.kr.setsu.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kr.setsu.entity.AttendanceInfo;
import com.kr.setsu.entity.DayInfo;

public interface AttendanceService {

	List<AttendanceInfo> shuTaiKin(AttendanceInfo attendanceInfo);

	List<AttendanceInfo> getAttendance(AttendanceInfo attendanceInfo);
	
	List<AttendanceInfo> getAttendanceByMonths(AttendanceInfo attendanceInfo);
	
	List<DayInfo> getDateByMonths(String date) throws ParseException;
	
	List<AttendanceInfo> updateAttendance(AttendanceInfo attendanceInfo);
	
	String getexcel(AttendanceInfo attendanceInfo) throws IOException;
	Integer updateWorkRecord(AttendanceInfo attendanceInfo);
}
