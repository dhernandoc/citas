
angular.module('dameCita').controller('NewCitaController', function ($scope, $location, locationParser, CitaResource , ProfesionalResource, PacienteResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.cita = $scope.cita || {};
    
    $scope.idProfesionalList = ProfesionalResource.queryAll(function(items){
        $scope.idProfesionalSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("idProfesionalSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.cita.idProfesional = {};
            $scope.cita.idProfesional.id = selection.value;
        }
    });
    
    $scope.idPacienteList = PacienteResource.queryAll(function(items){
        $scope.idPacienteSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("idPacienteSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.cita.idPaciente = {};
            $scope.cita.idPaciente.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Cita/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        CitaResource.save($scope.cita, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Cita");
    };
});