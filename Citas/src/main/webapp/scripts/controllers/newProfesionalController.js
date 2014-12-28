
angular.module('dameCita').controller('NewProfesionalController', function ($scope, $location, locationParser, ProfesionalResource , CitaResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.profesional = $scope.profesional || {};
    
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
            $scope.profesional.idCita = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.profesional.idCita.push(collectionItem);
            });
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Profesionals/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        ProfesionalResource.save($scope.profesional, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Profesionals");
    };
});