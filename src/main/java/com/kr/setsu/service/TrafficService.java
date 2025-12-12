package com.kr.setsu.service;

import java.util.List;

import com.kr.setsu.entity.TrafficInfo;

public interface TrafficService {

	List<TrafficInfo> getTrafficList();
	List<TrafficInfo> getTrafficList(TrafficInfo trafficInfo);
	List<TrafficInfo> deleteById(List<TrafficInfo> list);
	List<TrafficInfo> addTraffic(List<TrafficInfo> list);
	List<TrafficInfo> updateTraffic(TrafficInfo trafficInfo);

}        
