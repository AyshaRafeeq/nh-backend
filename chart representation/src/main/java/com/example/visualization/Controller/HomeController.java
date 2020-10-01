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
	
	Integer id;
	String name;
	@Autowired
	dataDAO dataDAO;
	
  	@Autowired
	viewServiceImpl service;
	
	@RequestMapping("/teacher")
	public String showHome() {
		//System.out.println("here");
		return "home";
	}
	
	
	@RequestMapping("/student")
	public String studshowHome() {
		return "student";
	}
	
	@RequestMapping("/index")
	public String index(Data data) {
		id = data.getTestid();
		return "index";
	}
	
	@RequestMapping("/listStudents")
	@GetMapping
	public ModelAndView list() {
		List<String> list =  service.getAllStudents();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("view");
		mv.addObject("name",list);
		return mv;
	}
	
	@CrossOrigin(origins = "http://localhost:8082")
	@RequestMapping("/linechartdata")
	@ResponseBody
	public String getDataFromDB(){
		List<Data> dataList = service.getStudentsbyid(id);
		System.out.println(dataList);
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
	
	@RequestMapping("/individualreport")
	public String report(String uname) {
		//System.out.println(uname);
		name= uname;
		return "individual";
	}
	
	@CrossOrigin(origins = "http://localhost:8082")
	@RequestMapping("/individualstuddata")
	@ResponseBody
	public String getData(){
		List<Data> dataList = service.getStudentsbyname(name);
		System.out.println(dataList);
		JsonArray jsonArrayTestid = new JsonArray();
		JsonArray jsonArrayMarks = new JsonArray();
		JsonObject jsonObject = new JsonObject();
		dataList.forEach(data->{
			jsonArrayTestid.add(data.getTestid());
			jsonArrayMarks.add(data.getMarks());
		});
		jsonObject.add("testid", jsonArrayTestid);
		jsonObject.add("marks", jsonArrayMarks);
		return jsonObject.toString();
	}
	
	
}
