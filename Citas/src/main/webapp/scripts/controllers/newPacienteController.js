
angular.module('dameCita').controller('NewPacienteController', function ($scope, $location, locationParser, PacienteResource , CitaResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.paciente = $scope.paciente || {};
    
    $scope.idCitaList = CitaResource.queryAll(function(items){
        $scope.idCitaSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("idCitaSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.paciente.idCita = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.paciente.idCita.push(collectionItem);
            });
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Pacientes/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        PacienteResource.save($scope.paciente, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Pacientes");
    };
});