package com.sc.oa.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.Schedule;
import com.sc.oa.domain.User;

public interface TaskService extends DaoSupport<Schedule>{

	public JSONArray getTasks(String month);
	
	public JSONArray getMine(User user,String month);
	
	public void delete(Schedule schedule);
}
