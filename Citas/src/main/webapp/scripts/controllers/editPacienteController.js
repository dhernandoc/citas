

angular.module('dameCita').controller('EditPacienteController', function($scope, $routeParams, $location, PacienteResource , CitaResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.paciente = new PacienteResource(self.original);
            CitaResource.queryAll(function(items) {
                $scope.idCitaSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.paciente.idCita){
                        $.each($scope.paciente.idCita, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.idCitaSelection.push(labelObject);
                                $scope.paciente.idCita.push(wrappedObject);
                            }
                        });
                        self.original.idCita = $scope.paciente.idCita;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Pacientes");
        };
        PacienteResource.get({PacienteId:$routeParams.PacienteId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.paciente);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.paciente.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Pacientes");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Pacientes");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.paciente.$remove(successCallback, errorCallback);
    };
    
    $scope.idCitaSelection = $scope.idCitaSelection || [];
    $scope.$watch("idCitaSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.paciente) {
            $scope.paciente.idCita = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.paciente.idCita.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});