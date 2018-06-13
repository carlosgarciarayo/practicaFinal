package com.example.demo.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pr_proyectos")
public class Proyecto implements Serializable {

	@Id
	@Column(name = "ID_PROYECTO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idProyecto;
	
	@Column(name = "TX_DESCRIPCIÓN")
	private String descripcion;
	
	@Column(name = "F_INICIO")
	private Date fInicio;
	
	@Column(name = "F_FIN")
	private Date fFin;
	
	@Column(name = "F_BAJA")
	private Date fBaja;
	
	@Column(name = "TX_LUGAR")
	private String lugar;
	
	@Column(name = "TX_OBSERVACIONES")
	private String observaciones;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "pr_empleados_proyecto", 
	joinColumns = @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO"), 
	inverseJoinColumns = @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "ID_EMPLEADO"))
	private List<Empleado> empleados;
	
	public Proyecto() {
		
	}

	public Proyecto(String descripcion, Date fInicio, Date fFin, String lugar, String observaciones) {
		this.descripcion = descripcion;
		this.fInicio = fInicio;
		this.fFin = fFin;
		this.fBaja = null;
		this.lugar = lugar;
		this.observaciones = observaciones;
	}

	
	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getfInicio() {
		return fInicio;
	}

	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}

	public Date getfFin() {
		return fFin;
	}

	public void setfFin(Date fFin) {
		this.fFin = fFin;
	}

	public Date getfBaja() {
		return fBaja;
	}

	public void setfBaja(Date fBaja) {
		this.fBaja = fBaja;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
}
