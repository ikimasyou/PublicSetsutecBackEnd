package com.kr.setsu.service.serviceImpl;

import java.io.Console;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.kr.setsu.entity.AttendanceInfo;
import com.kr.setsu.entity.DayInfo;
import com.kr.setsu.mapper.AttendanceMapper;
import com.kr.setsu.mapper.UserMapper;
import com.kr.setsu.service.AttendanceService;
import com.samuraism.holidays.日本の祝休日;

import ch.qos.logback.core.joran.conditional.IfAction;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceMapper attendanceMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
    private PlatformTransactionManager transactionManager;

	
	private Logger logger = LoggerFactory.getLogger(AttendanceServiceImpl.class);
	
	private  HSSFWorkbook readFile(String filename) throws IOException {
		try (FileInputStream fis = new FileInputStream(filename)) {
			return new HSSFWorkbook(fis); // NOSONAR - should not be closed here
		}
	}
	
	@Override
	public List<AttendanceInfo> shuTaiKin(AttendanceInfo attendanceInfo) {
		if(attendanceInfo.getIsAttendance()){
			if(attendanceMapper.getAttendance(attendanceInfo).size()==0) {
				
				LocalDateTime currentTime = LocalDateTime.now();
				
				// Author:Muradil Date:2024/02/15
				// 期望是在这里获取到M_KINMU表中的行数来产生勤务ID,因此当两个线程同时获取到相同的个数时，
				// 可能会生成相同的勤务ID，因此在这里添加事物，考虑到加@Transaction注解会使得事物的粒度变大。
				// 因此在这里使用PlatfromTransactionManager类来创建事物。
		        
				DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
				
				//设定本次事物的事物隔离级别为默认的隔离级别。
		        transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		        //设定事物的传播行为，PROPAGATION_REQUIRED 表示：
		        // 1,如果当前没有事务，则创建一个新事务。
		        // 2,如果当前已存在一个事务，则加入到该事务中。
		        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		        // 获取事务状态
		        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

		        try {
		            // 在这里执行需要事务支持的操作
		        	long attendanceIdGenerate = attendanceMapper.getExistAttendanceCount()+1;
					String attendanceId =  String.format("%08d", attendanceIdGenerate);
					attendanceInfo.setAttendanceId(attendanceId);
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

			        // 格式化当前时间（可选）
			        String formattedTime = currentTime.format(formatter);
			        attendanceInfo.setBegin(formattedTime);
					attendanceMapper.shukkin(attendanceInfo);
		            // 如果需要手动回滚事务，调用以下语句
		            // transactionStatus.setRollbackOnly();
		            
		            // 提交事务
		            transactionManager.commit(transactionStatus);
		        } catch (Exception e) {
		            // 发生异常时回滚事务
		        	logger.error("An error occurred when execute the attendence logic, transaction rollback!", e);
		            transactionManager.rollback(transactionStatus);
		            e.printStackTrace(); // 可以选择抛出或处理异常
		        }
				// 
				
				
			}
		}else {
			// 该判断是为了检查当天是否已出勤
			List<AttendanceInfo> todayAttendance = attendanceMapper.getAttendance(attendanceInfo);
			if(todayAttendance.size()>0) {
				//该判断是为了检查当天是否已退勤，在未退勤的情况下退勤操作才会被执行
				boolean flag = false;
				
//				for(int i=0;i<todayAttendance.size();i++) {
//					if(todayAttendance.get(i).getEnd()!=null)
//						flag = true;
//				}
				if(!flag) {
					System.out.println("end time processing....");
					LocalDateTime currentTime = LocalDateTime.now();
					
					// Author:Muradil Date:2024/02/15
					// 期望是在这里获取到M_KINMU表中的行数来产生勤务ID,因此当两个线程同时获取到相同的个数时，
					// 可能会生成相同的勤务ID，因此在这里添加事物，考虑到加@Transaction注解会使得事物的粒度变大。
					// 因此在这里使用PlatfromTransactionManager类来创建事物。
			        
					DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
					
					//设定本次事物的事物隔离级别为默认的隔离级别。
			        transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
			        //设定事物的传播行为，PROPAGATION_REQUIRED 表示：
			        // 1,如果当前没有事务，则创建一个新事务。
			        // 2,如果当前已存在一个事务，则加入到该事务中。
			        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

			        // 获取事务状态
			        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

			        try {
			            // 在这里执行需要事务支持的操作
			        	long attendanceIdGenerate = attendanceMapper.getExistAttendanceCount()+1;
						String attendanceId =  String.format("%08d", attendanceIdGenerate);
						attendanceInfo.setAttendanceId(attendanceId);
				        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

				        // 格式化当前时间（可选）
				        String formattedTime = currentTime.format(formatter);
				        attendanceInfo.setEnd(formattedTime);
						System.out.println(attendanceInfo.getEnd());
				        attendanceMapper.taikin(attendanceInfo);
			            // 如果需要手动回滚事务，调用以下语句
			            // transactionStatus.setRollbackOnly();
			            
			            // 提交事务
			            transactionManager.commit(transactionStatus);
			        } catch (Exception e) {
			            // 发生异常时回滚事务
			        	logger.error("An error occurred when execute the attendence logic, transaction rollback!", e);
			            transactionManager.rollback(transactionStatus);
			            e.printStackTrace(); // 可以选择抛出或处理异常
			        }
					
					
				}
			}
		}
		attendanceInfo.setDate(attendanceInfo.getDate().substring(0, 7));
		return  getAttendance(attendanceInfo);
	}

	@Override
	public List<AttendanceInfo> getAttendance(AttendanceInfo attendanceInfo) {
	
		return attendanceMapper.getAttendance(attendanceInfo);
		
	}

	@Override
	public List<AttendanceInfo> getAttendanceByMonths(AttendanceInfo attendanceInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DayInfo> getDateByMonths(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		int days = getDaysOfMonth(sdf.parse(date));
		日本の祝休日 holidays = new 日本の祝休日();
		
		List<DayInfo> list  = new ArrayList<DayInfo>();
		for(int i=0;i<days;i++) {
			DayInfo dayInfo = new DayInfo();
			Calendar calendar = Calendar.getInstance();
			Date date1 = sdf.parse(date.substring(0,8) + (i + 1));
			calendar.setTime(date1);
			int num = (calendar.get(Calendar.DAY_OF_WEEK) + 7 - 1) % 7;
			
			boolean isHoliday =  holidays.is祝休日(LocalDate.of(Integer.parseInt(date.substring(0,4)), Integer.parseInt(date.substring(5,7).replaceAll("/", "")), i+1));
			dayInfo.setDate(date.substring(0,8) + (i + 1));
			if(num == 6 || num == 0) {
				dayInfo.setFlag(true);
			} else {
				dayInfo.setFlag(isHoliday);
			}
			dayInfo.setNumber((calendar.get(Calendar.DAY_OF_WEEK) + 7 - 1) % 7);
			list.add(dayInfo);
		}
		
		return list;
	}

	@Override
	public List<AttendanceInfo> updateAttendance(AttendanceInfo attendanceInfo) {
		// TODO Auto-generated method stub
		List<AttendanceInfo> list = getAttendance(attendanceInfo);
		if(list.isEmpty()) {
			attendanceMapper.insertAttendance(attendanceInfo);
		}else {
			attendanceMapper.updateAttendance(attendanceInfo);
		}
		
		attendanceInfo.setDate(attendanceInfo.getDate().substring(0, 7));
		return getAttendance(attendanceInfo);
	}

//	private int getBigestId(List<AttendanceInfo> list) {
//		int max = 0;
//		for (AttendanceInfo attendanceInfo : list) {
//			if (max < Integer.parseInt(attendanceInfo.getAttendanceId())) {
//				max = Integer.parseInt(attendanceInfo.getAttendanceId());
//			}
//		}
//		return max/
//	}


	
	private static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	
	@Override
	public String getexcel(AttendanceInfo attendanceInfo) throws IOException {
		// TODO Auto-generated method stub
	String s ="C:\\Users\\Administrator\\Documents\\excelforms\\kinmu.xls";
		try(HSSFWorkbook wb = readFile(s)){
			HSSFSheet sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			List<AttendanceInfo> list = attendanceMapper.getAttendance(attendanceInfo);
			HSSFRow row = sheet.getRow(2);
			double totalHour = 0;
			System.out.println("----------");
			String username = userMapper.getUserById(attendanceInfo.getUserId()).getUsername();
			row.getCell(1).setCellValue(username);
			System.out.println(row.getCell(1).getStringCellValue());
			for (int r = 0;r<list.size();r++) {
				
				row = sheet.getRow(r + 4);
				AttendanceInfo  a =list.get(r);
				for(int c=0;c<9;c++) {
//					HSSFCell cell = row.getCell(c);
					switch (c) {
					case 0:
						row.getCell(0).setCellValue(a.getDate());
//						System.out.println(row.getCell(0).getStringCellValue());
						break;
					
					case 1:
						row.getCell(1).setCellValue(a.getDivision());
						break;
						
					case 2:
						row.getCell(2).setCellValue(a.getBegin());
						break;
					
					case 3:
						row.getCell(3).setCellValue(a.getEnd());
						break;
						
					case 4:
						row.getCell(4).setCellValue(a.getBreakday());
						break;	
						
					case 5:
						row.getCell(5).setCellValue(a.getWorkhour());
						if(a.getWorkhour() != null) {
							
							String[] hStrings=a.getWorkhour().split(":");
							Double m = Double.parseDouble(hStrings[0]);
							Double n = Double.parseDouble(hStrings[1])/60;
							System.out.println(hStrings[0]+hStrings[1]);
							totalHour += (m+n);
						}
						break;
						
					case 6:
						row.getCell(6).setCellValue(a.getWorkcontent());
						break;	
						
					case 7:
						row.getCell(7).setCellValue(a.getNote());
						break;	
						
					case 8:
						row.getCell(8).setCellValue(a.getChangework());
						break;	
					default:
						break;
					}
				}
			}
			
			sheet.getRow(35).getCell(5).setCellValue(totalHour);
			if(totalHour>200) {
				sheet.getRow(36).getCell(5).setCellValue(totalHour-200);
			}else {
				sheet.getRow(36).getCell(5).setCellValue(0);
			}
			String abc ="C:\\Users\\Administrator\\Documents\\excelforms\\勤务表_"+username+".xls";
			FileOutputStream fileOut = new FileOutputStream(abc);
			wb.write(fileOut);
			s = "勤务表_"+username+".xls";
		}
	
		return s;
	}

	@Override
	public Integer updateWorkRecord(AttendanceInfo attendanceInfo) {

		return attendanceMapper.updateWorkRecord(attendanceInfo);
	}


}
