angular.module('company')
.service('employeeService', function($http){
	
	this.getEmployees = function(){
		return $http.get("/getEmployees");
	};
	
	this.addEmployee = function(nif,name,lastname1,lastname2,birthday,
			telefone1,telefone2,email,fAlta,civStatus,militaryServ){
		return $http({
    		url:'/addEmployee', 
            method: 'POST',
            params:{
            	nif: nif,
            	nombre: name,
            	apellido1: lastname1,
            	apellido2: lastname2,
            	fNacimiento: birthday,
            	telefono1: telefone1,
            	telefono2: telefone2,
            	email: email,
            	fAlta:fAlta,
            	edoCivil: civStatus,
            	servMilitar: militaryServ
            }
    	});
	};
	
	this.deleteEmployee = function(idEmployee){
		return $http({
    		url:'/deleteEmployee', 
            method: 'POST',
            params:{
            	idEmployee:idEmployee
            }
    	});
	};
});