package com.example.visualization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.visualization.dao.dataDAO;
import com.example.visualization.model.Data;

@Service
public class viewService{

	@Autowired 
	private dataDAO dao; 
	
	public List<Data> getAllUsers() {
		return dao.findAll();
	}
}
