package com.kr.setsu.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.metadata.HanaCallMetaDataProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.kr.setsu.entity.AttendanceInfo;
import com.kr.setsu.entity.DayInfo;
import com.kr.setsu.filter.RSAFilter;
import com.kr.setsu.service.AttendanceService;
import com.kr.setsu.service.UserService;
import com.kr.setsu.service.serviceImpl.UserServiceImpl;
import com.kr.setsu.util.HttpStatus;
import com.kr.setsu.util.JsonResult;
import com.samuraism.holidays.日本の祝休日;

@Controller
@RequestMapping("/attendance")
@CrossOrigin(origins ="*") 
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@RequestMapping(value = "/shuTaiKin",method = RequestMethod.POST)
	@ResponseBody
	JsonResult<List<AttendanceInfo>> shuTaiKin(@RequestBody AttendanceInfo attendanceInfo){
		// 1.attendanceInfo里面的值一定不全
		// 2.取出attendanceInfo里的status，进行判断，
		// 3.status如果出勤，就调用service的更新出勤时间的方法，需要取系统时间
		// 4.退勤时间亦然，
		// 5.如果都为空，就报错。
		// 6.service里将出勤/退勤时间存到数据库后，还要查询6条日期最近的数据，返回给controller
		// 在该方法体内，前端只需要传出勤或退勤的状态（isAttendance），userid和日期即可。
		logger.info(attendanceInfo.toString());
		JsonResult<List<AttendanceInfo>> jr = new JsonResult<List<AttendanceInfo>>(HttpStatus.OK);
		jr.setData(attendanceService.shuTaiKin(attendanceInfo));
		return jr;
	}
	

	
	@RequestMapping(value = "/getAttendance",method = RequestMethod.POST)
	@ResponseBody
	JsonResult<List<AttendanceInfo>> getAttendance(@RequestBody AttendanceInfo attendanceInfo){
		// 1.按月份使用service里查询6条日期最近的数据，返回给controller
		System.out.print(attendanceInfo);
		JsonResult<List<AttendanceInfo>> jr = new JsonResult<List<AttendanceInfo>>(HttpStatus.OK);
		jr.setData(attendanceService.getAttendance(attendanceInfo));
		return jr;
	}

	@RequestMapping(value = "/getAttendanceByMonths",method = RequestMethod.POST)
	@ResponseBody
	List<AttendanceInfo> getAttendanceByMonths(String date){
		// 1.service里根据月份查询所有日期最近的数据，返回给controller
		
		return null;
		
	}

	@RequestMapping(value = "/getDateByMonths",method = RequestMethod.POST)
	@ResponseBody	
	List<DayInfo> getDateByMonths(@RequestBody AttendanceInfo attendanceInfo){

//		 日本の祝休日 holidays = new 日本の祝休日();
//		boolean isHoliday =  holidays.is営業日(LocalDate.of(2022, 10, 20));
//		SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
//        sdf.applyPattern("yyyy");// a为am/pm的标记
//        Date date = new Date(0);// 获取当前时间 不一定是当前时间
//        String year = sdf.format(date);
		
		List<DayInfo> result= new ArrayList<>();
		try {
			result=attendanceService.getDateByMonths(attendanceInfo.getDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return result;
	}

	@RequestMapping(value = "/updateAttendance",method = RequestMethod.POST)
	@ResponseBody	
	List<AttendanceInfo> updateAttendance(@RequestBody AttendanceInfo attendanceInfo){
		// 1.attendanceInfo里面的值不全也当作全处理
		// 2.就调用service的更新整个勤务时间的方法，不需要取系统时间
		// 6.service里将出勤/退勤时间存到数据库后，还要查询6条日期最近的数据，返回给controller


		return attendanceService.updateAttendance(attendanceInfo);
	}
	
	@RequestMapping(value = "/getexcel",method = RequestMethod.POST)
	@ResponseBody
	Map<String, String> getexcel(@RequestBody AttendanceInfo attendanceInfo) throws IOException {
		Map<String, String> map = new HashMap<>();
		map.put("address", attendanceService.getexcel(attendanceInfo));
		return  map;
	}
	@PostMapping("/updateworkrecord")
	@ResponseBody
	Map<String,Object> updateworkrecord(@RequestBody AttendanceInfo attendanceInfo){
		Map<String,Object> map = new HashMap<>();
		if (attendanceService.updateWorkRecord(attendanceInfo)>0){
			map.put("status", HttpStatus.OK);
		}
		return map;
	}


}
