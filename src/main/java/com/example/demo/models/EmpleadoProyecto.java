package com.example.demo.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pr_empleados_proyecto")
public class EmpleadoProyecto implements Serializable {

	@EmbeddedId
	private EmpleadoProyectoPK id;
	
	@Column(name = "F_ALTA")
	private Date fAlta;
	
	public EmpleadoProyecto() {
		
	}

	public EmpleadoProyecto(EmpleadoProyectoPK id, Date fAlta) {
		super();
		this.id = id;
		this.fAlta = fAlta;
	}
	
	public EmpleadoProyecto(Integer idProject, Integer idEmployee, Date fAlta) {
		EmpleadoProyectoPK pk = new EmpleadoProyectoPK(idProject,idEmployee);
		this.id = pk;
		this.fAlta = fAlta;
	}
	
	public EmpleadoProyectoPK getId() {
		return id;
	}

	public void setId(EmpleadoProyectoPK id) {
		this.id = id;
	}

	public Date getfAlta() {
		return fAlta;
	}

	public void setfAlta(Date fAlta) {
		this.fAlta = fAlta;
	}
	
	
	
}
