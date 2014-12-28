

angular.module('dameCita').controller('EditProfesionalController', function($scope, $routeParams, $location, ProfesionalResource , CitaResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.profesional = new ProfesionalResource(self.original);
            CitaResource.queryAll(function(items) {
                $scope.idCitaSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.profesional.idCita){
                        $.each($scope.profesional.idCita, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.idCitaSelection.push(labelObject);
                                $scope.profesional.idCita.push(wrappedObject);
                            }
                        });
                        self.original.idCita = $scope.profesional.idCita;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Profesionals");
        };
        ProfesionalResource.get({ProfesionalId:$routeParams.ProfesionalId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.profesional);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.profesional.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Profesionals");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Profesionals");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.profesional.$remove(successCallback, errorCallback);
    };
    
    $scope.idCitaSelection = $scope.idCitaSelection || [];
    $scope.$watch("idCitaSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.profesional) {
            $scope.profesional.idCita = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.profesional.idCita.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});