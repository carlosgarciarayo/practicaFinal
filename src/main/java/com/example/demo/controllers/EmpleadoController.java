package com.example.demo.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Empleado;
import com.example.demo.repositories.EmpleadoProyectoRepository;
import com.example.demo.repositories.EmpleadoRepository;

@RestController
public class EmpleadoController {

	private EmpleadoRepository empleadoRepository;
	private EmpleadoProyectoRepository empProyRepository;

	@Autowired
	public EmpleadoController(EmpleadoRepository empleadoRep, EmpleadoProyectoRepository emplProyRep) {
		this.empProyRepository = emplProyRep;
		this.empleadoRepository = empleadoRep;
		
	}
	
	
	@RequestMapping(value="/getEmployees", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Empleado>> getEmployees(){
		return new ResponseEntity<List<Empleado>>(empleadoRepository.findByFBajaIsNull(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/addEmployee", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> addEmployee(@RequestParam(value="nif") String nif,@RequestParam(value="nombre") String nombre,
			@RequestParam(value="apellido1") String apellido1,@RequestParam(value="apellido2") String apellido2,
			@RequestParam(value="fNacimiento") String fNacimiento, @RequestParam(value="telefono1") String telefono1,
			@RequestParam(value="telefono2") String telefono2,@RequestParam(value="email") String email,
			@RequestParam(value="fAlta") String fAlta,
			@RequestParam(value="edoCivil") char edoCivil,@RequestParam(value="servMilitar") char servMilitar) throws ParseException{
		
		if(empleadoRepository.existsByNifAndNombreAndApellido1AndApellido2(nif,nombre,apellido1,apellido2)) {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date result = format.parse(fNacimiento);
		Date result2 = format.parse(fAlta);
		Empleado emp = new Empleado(nif,  nombre,  apellido1,  apellido2, result,  telefono1,
				 telefono2,  email, result2,  null,  edoCivil, servMilitar);
		empleadoRepository.save(emp);
		return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/deleteEmployee", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> deleteEmployee(@RequestParam(value="idEmployee") Integer idEmployee){
		if(empleadoRepository.existsById(idEmployee) && empProyRepository.findByIdIdEmpleado(idEmployee).isEmpty()) {
			Empleado emp = empleadoRepository.findById(idEmployee).get();
			emp.setfBaja(new Date());
			empleadoRepository.save(emp);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}
	
}
