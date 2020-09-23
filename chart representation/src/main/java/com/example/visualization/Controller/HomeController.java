package com.example.visualization.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.visualization.dao.dataDAO;
import com.example.visualization.model.Data;
import com.example.visualization.service.viewServiceImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class HomeController {
	
	
	@Autowired
	dataDAO dataDAO;
	
  @Autowired
	viewServiceImpl service;
	
	@RequestMapping("/")
	public String showHome() {
		//System.out.println("here");
		return "index";
	}
	
	
	@RequestMapping("/linechartdata")
	@ResponseBody
	public String getDataFromDB(){
		List<Data> dataList = service.getAllUsers();
		JsonArray jsonArrayUname = new JsonArray();
		JsonArray jsonArrayMarks = new JsonArray();
		JsonObject jsonObject = new JsonObject();
		dataList.forEach(data->{
			jsonArrayUname.add(data.getUname());
			jsonArrayMarks.add(data.getMarks());
		});
		jsonObject.add("uname", jsonArrayUname);
		jsonObject.add("marks", jsonArrayMarks);
		return jsonObject.toString();
	}
	
	
}
