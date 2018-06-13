package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Empleado;
import com.example.demo.models.EmpleadoProyecto;
import com.example.demo.models.EmpleadoProyectoPK;
import com.example.demo.repositories.EmpleadoProyectoRepository;
import com.example.demo.repositories.EmpleadoRepository;

@RestController
public class EmpleadoProyectoController {

	private EmpleadoProyectoRepository empProyRepository;
	private EmpleadoRepository empRepository;
	
	@Autowired
	public EmpleadoProyectoController(EmpleadoProyectoRepository empProyRep,
			EmpleadoRepository empRep) {
		this.empProyRepository = empProyRep;
		this.empRepository = empRep;
	}
	
	@RequestMapping(value="/getEmployeesByProject/{idProject}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Empleado>> getEmployeesByProject(@PathVariable Integer idProject){
		List<EmpleadoProyecto> l = empProyRepository.findByIdIdProyecto(idProject);
		List<Empleado> employeesByProjectList = new ArrayList<Empleado>();
		for(EmpleadoProyecto empProy:l) {
			Empleado emp = empRepository.findByIdEmpleado(empProy.getId().getIdEmpleado());
			employeesByProjectList.add(emp);
			}
		return new ResponseEntity<List<Empleado>>(employeesByProjectList,HttpStatus.OK);
	}
	
	@RequestMapping(value="/assignEmployee", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> assignEmployee(@RequestParam(name="idProyecto") Integer idProject,
			@RequestParam(name="idEmpleado") Integer idEmployee){
		EmpleadoProyecto empProj = new EmpleadoProyecto(idProject, idEmployee, new Date());
		empProyRepository.save(empProj);
		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/dropEmployee", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> dropEmployee(@RequestParam(name="idProyecto") Integer idProject,
			@RequestParam(name="idEmpleado") Integer idEmployee){
		EmpleadoProyectoPK empProj = new EmpleadoProyectoPK(idProject, idEmployee);
		
		empProyRepository.deleteById(empProj);
		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}
}
