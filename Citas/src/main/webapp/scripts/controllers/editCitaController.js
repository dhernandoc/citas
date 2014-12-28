

angular.module('dameCita').controller('EditCitaController', function($scope, $routeParams, $location, CitaResource , ProfesionalResource, PacienteResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.cita = new CitaResource(self.original);
            ProfesionalResource.queryAll(function(items) {
                $scope.idProfesionalSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.cita.idProfesional && item.id == $scope.cita.idProfesional.id) {
                        $scope.idProfesionalSelection = labelObject;
                        $scope.cita.idProfesional = wrappedObject;
                        self.original.idProfesional = $scope.cita.idProfesional;
                    }
                    return labelObject;
                });
            });
            PacienteResource.queryAll(function(items) {
                $scope.idPacienteSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.cita.idPaciente && item.id == $scope.cita.idPaciente.id) {
                        $scope.idPacienteSelection = labelObject;
                        $scope.cita.idPaciente = wrappedObject;
                        self.original.idPaciente = $scope.cita.idPaciente;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Cita");
        };
        CitaResource.get({CitaId:$routeParams.CitaId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.cita);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.cita.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Cita");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Cita");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.cita.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("idProfesionalSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.cita.idProfesional = {};
            $scope.cita.idProfesional.id = selection.value;
        }
    });
    $scope.$watch("idPacienteSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.cita.idPaciente = {};
            $scope.cita.idPaciente.id = selection.value;
        }
    });
    
    $scope.get();
});