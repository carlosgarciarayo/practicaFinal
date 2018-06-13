$(document).ready(function(){
    $('.collapsible').collapsible();
  });

var app = angular.module('projects');

app.controller('projectController', function($scope,$http,projectService){
	
	var projectCtrl = this;
	projectCtrl.projects = [];
	projectCtrl.projectsNotFinalized = [];
	projectCtrl.selectedProject = 0;
	projectCtrl.selectedProjectEmployees = [];
	projectCtrl.employees = [];
	var idEmployees = [];
	var addEmployees = [];
	var dropEmployees = [];
	
	
	projectService.getProjects().success(function(data){
		projectCtrl.projects = data;
	});
	
	projectService.getProjectsNotFinalized().success(function(data){
		projectCtrl.projectsNotFinalized = data;
	});
	
	projectService.getEmployees().success(function(data){
		projectCtrl.employees = data;
	});
	
	projectService.getEmployeesByProject(projectCtrl.selectedProject).success(function(data){
		projectCtrl.selectedProjectEmployees = data;
	});
	
	this.addProject = function(){
		
		var descripcion = $scope.project.descripcion;
    	var fInicio = $scope.project.fInicio;
    	var fFin = $scope.project.fFin;
    	var lugar = $scope.project.lugar;
    	var observaciones = $scope.project.observaciones;

		
		projectService.addProject(descripcion, fInicio, fFin,
				lugar, observaciones).success(function(data,json){
					if(data == false){
						  Materialize.toast('No se puede dar de alta el proyecto, fecha inicio debe ser anterior a la fecha final', 4000);
					}
					else{
						location.reload(true);
					}

		});
		
	};
	
	$scope.deleteProject = function(idProject){
		projectService.deleteProject(idProject).success(function(data,json){
			if(data == false){
				  Materialize.toast('No se puede dar de baja el proyecto porque tiene asignado al menos un recurso', 4000);
			}
			else{
				  Materialize.toast('Se ha dado de baja correctamente!', 4000);

			}
    		if(!json.error) {
    			projectService.getProjects().success(function(data){
    				projectCtrl.projects = data;
    	    	});
    		}
    	});

	};
	
	$scope.setProjectSelected = function(idProyecto){
		projectCtrl.selectedProject = idProyecto;
		idEmployees = [];
		addEmployees = [];
		dropEmployees = [];
		projectService.getEmployeesByProject(projectCtrl.selectedProject).success(function(data){
			projectCtrl.selectedProjectEmployees = data;
			for (var i = 0; i < projectCtrl.selectedProjectEmployees.length; i++){
				idEmployees.push(projectCtrl.selectedProjectEmployees[i].idEmpleado);
				addEmployees.push(projectCtrl.selectedProjectEmployees[i].idEmpleado);
				}
			for (var i = 0; i < projectCtrl.employees.length; i++){
				
				if(idEmployees.includes(projectCtrl.employees[i].idEmpleado)){
					projectCtrl.employees[i].checked = true;
				}
				else {
					projectCtrl.employees[i].checked = false;
				}
			}
		});
		
	};
	
	$scope.checkEmployee = function(idEmpleado,checked){
		
		if(checked){
			if(!addEmployees.includes(idEmpleado)){
				addEmployees.push(idEmpleado)
			}
			if(dropEmployees.includes(idEmpleado)){
				var index = dropEmployees.indexOf(idEmpleado);
				dropEmployees.splice(index,1);
			}
		}
		else{
			if(addEmployees.includes(idEmpleado)){
				var index = addEmployees.indexOf(idEmpleado);
				addEmployees.splice(index,1);
			}
			if(!dropEmployees.includes(idEmpleado)){
				dropEmployees.push(idEmpleado);
			}
		}
	};
	
	$scope.saveChanges = function(){
		for(var i = 0; i < addEmployees.length; i++){
			if(!idEmployees.includes(addEmployees[i])){
				projectService.assignEmployee(projectCtrl.selectedProject,addEmployees[i]);
			}
		}
		for(var i = 0; i < dropEmployees.length; i++){
			if(idEmployees.includes(dropEmployees[i])){
				projectService.dropEmployee(projectCtrl.selectedProject,dropEmployees[i]);
			}
		}
	}

	
});