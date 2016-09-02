package com.sc.oa.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.oa.base.DaoSupportImpl;
import com.sc.oa.domain.Schedule;
import com.sc.oa.domain.User;
import com.sc.oa.service.TaskService;
@Service
@Transactional
public class TaskServiceImpl extends DaoSupportImpl<Schedule> implements TaskService{

	public JSONArray getTasks(String month) {
		JSONArray array = new JSONArray();      //新建JSON数组对象
        List<Schedule> list=getSession().createQuery("from Schedule where date like '"+month+"%' order by id asc")
				.list();
        for(Schedule list2:list){
        	String datestr=Integer.toString(list2.getDate().getDate());
        	String date=month+"-"+datestr;
//        	System.out.println(date);
        	JSONObject obj = new JSONObject();
            obj.put("id", list2.getId());
            obj.put("task", list2.getContext());
            obj.put("builddate", date);
            array.add(obj);           
        }
        return array;
	}
	
	public JSONArray getMine(User user,String month){
		
		JSONArray array = new JSONArray();      //新建JSON数组对象

        List<Schedule> list=getSession().createQuery("from Schedule where appuser=? and date like '"+month+"%' or state=1 order by id asc")
        		.setParameter(0, user)
				.list();
        System.out.println("jieguo chang du"+list.size());
        for(Schedule list2:list){
        	String datestr=Integer.toString(list2.getDate().getDate());
        	String date=month+"-"+datestr;
//        	System.out.println(date);
        	JSONObject obj = new JSONObject();
            obj.put("id", list2.getId());
            obj.put("task", list2.getContext());
            obj.put("builddate", date);
            array.add(obj);           
        }
        System.out.println("service"+array);
        return array;
	}
	
	
	public void delete(Schedule schedule){
		getSession().delete(schedule);
	}

}
