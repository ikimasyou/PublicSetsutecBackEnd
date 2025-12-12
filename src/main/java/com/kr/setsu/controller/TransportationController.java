package com.kr.setsu.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kr.setsu.entity.TransportationInfo;
import com.kr.setsu.service.TransportationService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/transportation")
public class TransportationController {

	@Autowired
	TransportationService transportationService;
	
	Logger logger=LoggerFactory.getLogger(TransportationController.class);
	
	@GetMapping("/submit")
	public boolean submitHandler(@RequestParam String message) {
		logger.info(message);
		return true;
	}
	
	@PostMapping("/submit2")
	public Integer submitHandler2(@RequestBody TransportationInfo transportationInfo) {
		logger.info(transportationInfo.toString());
		
		Integer res;
		if(transportationService.getTransportationInfoByDate(transportationInfo.getDate()).isEmpty()) {
		   res=transportationService.insertTransportationInfo(transportationInfo);
		}else {
			res=transportationService.updateTransportationInfoByDate(transportationInfo);
		}
		return res;
	}
	
	@GetMapping("/getAllTransportationInfo")
	public List<TransportationInfo> getAllTranfportationInfoHandler(){
		logger.info("entered getAllTranfportationInfoHandler");
		transportationService.getAllTransportationInfo().forEach(data->System.out.println(data));
		return transportationService.getAllTransportationInfo();
	}
	
	@PostMapping("/deleteTransportationInfo")
	public Integer deleteTransportationInfoHandler(@RequestBody TransportationInfo transportationInfo) {
		logger.info("entered deleteTransportationInfoHandler");
		logger.info(transportationInfo.toString());
		return transportationService.deleteTransportationInfoByDate(transportationInfo);
	}
}
