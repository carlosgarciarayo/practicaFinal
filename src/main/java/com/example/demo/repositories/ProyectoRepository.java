package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto,Integer>{
	
	public List<Proyecto> findByFBajaIsNull();
	
	public List<Proyecto> findByFBajaIsNullAndFFinIsGreaterThan(Date date);
}
