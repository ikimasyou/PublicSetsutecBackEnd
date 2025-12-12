package com.kr.setsu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kr.setsu.entity.TrafficInfo;
import com.kr.setsu.entity.UserInfo;
import com.kr.setsu.mapper.TrafficMapper;
import com.kr.setsu.mapper.UserMapper;
import com.kr.setsu.service.TrafficService;
import com.kr.setsu.service.UserService;

@Service
public class TrafficServiceImpl implements TrafficService{
	
	@Autowired
	private TrafficMapper trafficMapper;

	@Override
	public List<TrafficInfo> getTrafficList() {
		// TODO Auto-generated method stub
		return trafficMapper.getTrafficList();
	}

	@Override
	public List<TrafficInfo> updateTraffic(TrafficInfo trafficInfo) {
		// TODO Auto-generated method stub
		if(trafficInfo.getTrafficId() != null && !("".equals(trafficInfo.getTrafficId()))) {
			trafficMapper.updateTraffic(trafficInfo);
			TrafficInfo traffic = new TrafficInfo();
			traffic.setDate(trafficInfo.getDate().substring(0, 7));
			traffic.setUserId(trafficInfo.getUserId());
			return trafficMapper.getTrafficUserMonthList(traffic);
		}
		List<TrafficInfo> trafficlist = trafficMapper.getTrafficList();
		int count = trafficlist.size();
		String newId = "";
		if(count == 0)
		{
			newId = "1";
		}
		else {
			newId = String.valueOf(Integer.parseInt(trafficlist.get(count-1).getTrafficId())+1);
		}
		trafficInfo.setTrafficId(newId);
		trafficMapper.insertTraffic(trafficInfo);
		TrafficInfo traffic = new TrafficInfo();
		traffic.setDate(trafficInfo.getDate().substring(0, 7));
		traffic.setUserId(trafficInfo.getUserId());
		return trafficMapper.getTrafficUserMonthList(traffic);
	}

	@Override
	public List<TrafficInfo> deleteById(List<TrafficInfo> list) {
		// TODO Auto-generated method stub
		for(int i = 0; i < list.size();i++)
		{
			trafficMapper.deleteTraffic(list.get(i).getTrafficId());
		}
		TrafficInfo traffic = new TrafficInfo();
		traffic.setDate(list.get(0).getDate().substring(0, 7));
		traffic.setUserId(list.get(0).getUserId());
		return trafficMapper.getTrafficUserMonthList(traffic);
	}

	@Override     
	public List<TrafficInfo> addTraffic(List<TrafficInfo> list) {
		// TODO Auto-generated method stub
		TrafficInfo traffic = new TrafficInfo();
		traffic.setDate(list.get(0).getDate().substring(0, 7));
		traffic.setUserId(list.get(0).getUserId());
		
		for(int i = 0; i < list.size();i++)
		{
			List<TrafficInfo> trafficlist = trafficMapper.getTrafficList();
			int count = trafficlist.size();
			String newId = String.valueOf(Integer.parseInt(trafficlist.get(count-1).getTrafficId())+1);
			list.get(i).setTrafficId(newId);
			trafficMapper.insertTraffic(list.get(i));
		}
		return trafficMapper.getTrafficUserMonthList(traffic);
	}

	@Override
	public List<TrafficInfo> getTrafficList(TrafficInfo trafficInfo) {
		// TODO Auto-generated method stub
		return trafficMapper.getTrafficUserMonthList(trafficInfo);
	}

}
