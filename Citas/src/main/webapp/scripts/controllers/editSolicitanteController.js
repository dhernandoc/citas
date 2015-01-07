

angular.module('citas').controller('EditSolicitanteController', function($scope, $routeParams, $location, SolicitanteResource , CitaResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.solicitante = new SolicitanteResource(self.original);
            CitaResource.queryAll(function(items) {
                $scope.idCitaSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.solicitante.idCita){
                        $.each($scope.Solicitante.idCita, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.idCitaSelection.push(labelObject);
                                $scope.solicitante.idCita.push(wrappedObject);
                            }
                        });
                        self.original.idCita = $scope.solicitante.idCita;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Solicitantes");
        };
        SolicitanteResource.get({SolicitanteId:$routeParams.SolicitanteId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.solicitante);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.solicitante.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Solicitantes");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Solicitantes");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.solicitante.$remove(successCallback, errorCallback);
    };
    
    $scope.idCitaSelection = $scope.idCitaSelection || [];
    $scope.$watch("idCitaSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.solicitante) {
            $scope.solicitante.idCita = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.solicitante.idCita.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});