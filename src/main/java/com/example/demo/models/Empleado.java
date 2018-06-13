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
@Table(name = "em_empleados")
public class Empleado implements Serializable {
	
	@Id
	@Column(name = "ID_EMPLEADO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idEmpleado;
	
	@Column(name = "TX_NIF")
	private String nif;
	
	@Column(name = "TX_NOMBRE")
	private String nombre;
	
	@Column(name = "TX_APELLIDO1")
	private String apellido1;

	@Column(name = "TX_APELLIDO2")
	private String apellido2;
	
	@Column(name = "F_NACIMIENTO")
	private Date fNacimiento;
	
	@Column(name = "N_TELEFONO1")
	private String telefono1;

	@Column(name = "N_TELEFONO2")
	private String telefono2;
	
	@Column(name = "TX_EMAIL")
	private String email;
	
	@Column(name = "F_ALTA")
	private Date fAlta;
	
	@Column(name = "F_BAJA")
	private Date fBaja;
	
	@Column(name = "CX_EDOCIVIL")
	private char edoCivil;
	
	@Column(name = "B_SERVMILITAR")
	private char servMilitar;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "pr_empleados_proyecto", 
	joinColumns = @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "ID_EMPLEADO"), 
	inverseJoinColumns = @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO"))
	private List<Proyecto> proyectos;
	
	public Empleado() {
		
	}

	public Empleado(String nif, String nombre, String apellido1, String apellido2, Date fNacimiento, String telefono1,
			String telefono2, String email, Date fAlta, Date fBaja, char edoCivil, char servMilitar) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fNacimiento = fNacimiento;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.email = email;
		this.fAlta = fAlta;
		this.fBaja = fBaja;
		this.edoCivil = edoCivil;
		this.servMilitar = servMilitar;
	}

	
	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Date getfNacimiento() {
		return fNacimiento;
	}

	public void setfNacimiento(Date fNacimiento) {
		this.fNacimiento = fNacimiento;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getfAlta() {
		return fAlta;
	}

	public void setfAlta(Date fAlta) {
		this.fAlta = fAlta;
	}

	public Date getfBaja() {
		return fBaja;
	}

	public void setfBaja(Date fBaja) {
		this.fBaja = fBaja;
	}

	public char getEdoCivil() {
		return edoCivil;
	}

	public void setEdoCivil(char edoCivil) {
		this.edoCivil = edoCivil;
	}

	public char getServMilitar() {
		return servMilitar;
	}

	public void setServMilitar(char servMilitar) {
		this.servMilitar = servMilitar;
	}
	
	
}
