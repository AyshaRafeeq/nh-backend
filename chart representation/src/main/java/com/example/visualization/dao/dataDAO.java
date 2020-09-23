package com.example.visualization.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.visualization.model.Data;

@Repository
public interface dataDAO extends JpaRepository<Data,String>{
}
