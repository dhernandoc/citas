
angular.module('citas').controller('NewSolicitanteController', function ($scope, $location, locationParser, SolicitanteResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.solicitante = $scope.solicitante || {};
    
   
    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Solicitantes/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        SolicitanteResource.save($scope.solicitante, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Solicitantes");
    };
});