package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {
	
	public Empleado findByIdEmpleado(Integer idEmpleado);
	
	public List<Empleado> findByFBajaIsNull();
	
	public Boolean existsByNifAndNombreAndApellido1AndApellido2(String nif, String nombre, String apellido1, String apellido2);
}
