package com.example.demo.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.example.demo.models.Proyecto;
import com.example.demo.repositories.EmpleadoProyectoRepository;
import com.example.demo.repositories.ProyectoRepository;

@RestController
public class ProyectoController {
	
	private ProyectoRepository proyectoRepository;
	private EmpleadoProyectoRepository empProyRepository;
	
	@Autowired
	public ProyectoController(ProyectoRepository proyRep, EmpleadoProyectoRepository empProyRep) {
		this.proyectoRepository = proyRep;
		this.empProyRepository = empProyRep;
	}
	
	@RequestMapping(value="/getProject/{idProject}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Proyecto> getProjects(@PathVariable Integer idProject){
		return new ResponseEntity<Proyecto>(proyectoRepository.findById(idProject).get(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/getProjectsNotFinalized", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Proyecto>> getProjectsNotFinalized(){
		return new ResponseEntity<List<Proyecto>>(proyectoRepository.findByFBajaIsNullAndFFinIsGreaterThan(new Date()),HttpStatus.OK);
	}
	
	@RequestMapping(value="/getProjects", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Proyecto>> getProjects(){
		return new ResponseEntity<List<Proyecto>>(proyectoRepository.findByFBajaIsNull(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteProject", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> deleteProject(@RequestParam(value="idProyecto") Integer idProyecto){
		if(proyectoRepository.existsById(idProyecto) && empProyRepository.findByIdIdProyecto(idProyecto).isEmpty()) {
			Proyecto proy = proyectoRepository.findById(idProyecto).get();
			proy.setfBaja(new Date());
			proyectoRepository.save(proy);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/addProject", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> addProject(@RequestParam(value="descripcion")String descripcion, 
			@RequestParam(value="fInicio") String fInicio,@RequestParam(value="fFin") String fFin,
			@RequestParam(value="lugar") String lugar,
			@RequestParam(value="observaciones")String observaciones) throws ParseException{
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date result = format.parse(fInicio);
		Date result2 = format.parse(fFin);
		if(result.before(result2)) {
			Proyecto p = new Proyecto(descripcion, result, result2, lugar, observaciones);
			proyectoRepository.save(p);
			
			return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}
	
}
