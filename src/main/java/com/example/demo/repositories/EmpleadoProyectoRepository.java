package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.EmpleadoProyecto;
import com.example.demo.models.EmpleadoProyectoPK;

public interface EmpleadoProyectoRepository extends JpaRepository<EmpleadoProyecto,EmpleadoProyectoPK>{

	public List<EmpleadoProyecto> findByIdIdProyecto(Integer idProyecto);
	public List<EmpleadoProyecto> findByIdIdEmpleado(Integer idEmpleado);
	
}
