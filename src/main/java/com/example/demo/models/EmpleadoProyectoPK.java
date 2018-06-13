package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmpleadoProyectoPK implements Serializable {

	@Column(name = "ID_PROYECTO")
	private Integer idProyecto;
	
	@Column(name = "ID_EMPLEADO")
	private Integer idEmpleado;
	
	public EmpleadoProyectoPK() {
		
	}

	public EmpleadoProyectoPK(Integer idProyecto, Integer idEmpleado) {
		super();
		this.idProyecto = idProyecto;
		this.idEmpleado = idEmpleado;
	}

	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	
}
