
	 
var app = angular.module('company',['ui.materialize','720kb.datepicker']);

app.controller('employeeController',function($scope,$http,employeeService){
	
	var company = this;
	company.employees = [];
	
	employeeService.getEmployees().success(function(data){
		company.employees = data;
	});
	
	this.addEmployee = function(){
		var nombre = $scope.employee.nombre;
    	var apellido1 = $scope.employee.apellido1;
    	var apellido2 = $scope.employee.apellido2;
    	var nif = $scope.employee.nif;
    	var telefono1 = $scope.employee.telefono1;
    	var telefono2 = $scope.employee.telefono2;
		var email = $scope.employee.email;
		var fNacimiento = $scope.employee.fNacimiento;
		var edoCivil = $scope.employee.edoCivil;
		var fAlta = $scope.employee.fAlta;
		var servMilitar = $scope.employee.servMilitar; 
		
		employeeService.addEmployee(nif,nombre,apellido1,apellido2,fNacimiento,
				telefono1,telefono2,email,fAlta,edoCivil,servMilitar).success(function(data,json){
					if(data == false){
						  Materialize.toast('No se puede dar de alta el empleado, ya existe un empleado con NIF, nombre y apellidos iguales', 4000);
					}
					else{
						  Materialize.toast('Se ha dado de baja correctamente!', 4000);
						  if(!json.error) location.reload(true);
					}
		});
	};

	$scope.deleteEmployee = function(idEmployee){
		employeeService.deleteEmployee(idEmployee).success(function(data,json){
			if(data == false){
				  Materialize.toast('No se puede dar de baja el empleado porque tiene asignado al menos un proyecto', 4000);
			}
			else{
				  Materialize.toast('Se ha dado de baja correctamente!', 4000);
			}
    		if(!json.error) {
    			employeeService.getEmployees().success(function(data){
    	    		company.employees = data;
    	    	});
    		}
    	});

	};
});


