package com.kr.setsu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kr.setsu.entity.TrafficInfo;
import com.kr.setsu.entity.UserInfo;
import com.kr.setsu.service.TrafficService;
import com.kr.setsu.service.UserService;

@Controller
@RequestMapping("/traffic")
public class TrafficController {

	@Autowired
	private TrafficService trafficService;

	@RequestMapping(value = "/getTraffic", method = RequestMethod.POST)
	@ResponseBody
	List<TrafficInfo> getTraffic(@RequestBody TrafficInfo trafficInfo) {
		return trafficService.getTrafficList(trafficInfo);

	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	List<TrafficInfo> insertTraffic(@RequestBody List<TrafficInfo> list) {
		return trafficService.addTraffic(list);

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	List<TrafficInfo> deleteTraffic(@RequestBody List<TrafficInfo> list) {
		return trafficService.deleteById(list);	
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	List<TrafficInfo> updateTraffic(@RequestBody TrafficInfo trafficInfo) {
		trafficService.updateTraffic(trafficInfo);
		return trafficService.updateTraffic(trafficInfo);

	}
}
