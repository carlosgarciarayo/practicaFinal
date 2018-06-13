angular.module('projects', ['ui.materialize','720kb.datepicker'])
.service('projectService',function($http){
	
	this.getProjects = function(){
		return $http.get('/getProjects');
	};
	
	this.getProjectsNotFinalized = function(){
		return $http.get('/getProjectsNotFinalized');
	};
	
	this.getEmployeesByProject = function(idProject){
		return $http.get('/getEmployeesByProject/'+idProject);
	};
	
	this.getEmployees = function(){
		return $http.get('/getEmployees');
	}
	
	this.addProject = function(descripcion, fInicio, fFin, lugar,
			observaciones){
		return $http({
    		url:'/addProject', 
            method: 'POST',
            params:{
            	descripcion: descripcion,
            	fInicio: fInicio,
            	fFin: fFin,
            	lugar: lugar,
            	observaciones: observaciones
            }
    	});
	};

	this.deleteProject = function(idProject){
		return $http({
    		url:'/deleteProject', 
            method: 'POST',
            params:{
            	idProyecto:idProject
            }
    	});
	};
	
	this.assignEmployee = function(idProject,idEmployee){
		return $http({
			url:'/assignEmployee',
			method: 'POST',
			params: {
				idProyecto: idProject,
				idEmpleado: idEmployee
			}
		});
	};
	
	this.dropEmployee = function(idProject,idEmployee){
		return $http({
			url:'/dropEmployee',
			method: 'POST',
			params: {
				idProyecto: idProject,
				idEmpleado: idEmployee
			}
		});
	};
	
});