'use strict';

angular.module('citas',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Cita',{templateUrl:'views/Cita/search.html',controller:'SearchCitaController'})
      .when('/Cita/new',{templateUrl:'views/Cita/detail.html',controller:'NewCitaController'})
      .when('/Cita/edit/:CitaId',{templateUrl:'views/Cita/detail.html',controller:'EditCitaController'})
      .when('/Members',{templateUrl:'views/Member/search.html',controller:'SearchMemberController'})
      .when('/Members/new',{templateUrl:'views/Member/detail.html',controller:'NewMemberController'})
      .when('/Members/edit/:MemberId',{templateUrl:'views/Member/detail.html',controller:'EditMemberController'})
      .when('/Solicitantes',{templateUrl:'views/Solicitante/search.html',controller:'SearchSolicitanteController'})
      .when('/Solicitantes/new',{templateUrl:'views/Solicitante/detail.html',controller:'NewSolicitanteController'})
      .when('/Solicitantes/edit/:SolicitanteId',{templateUrl:'views/Solicitante/detail.html',controller:'EditSolicitanteController'})
      .when('/Profesionals',{templateUrl:'views/Profesional/search.html',controller:'SearchProfesionalController'})
      .when('/Profesionals/new',{templateUrl:'views/Profesional/detail.html',controller:'NewProfesionalController'})
      .when('/Profesionals/edit/:ProfesionalId',{templateUrl:'views/Profesional/detail.html',controller:'EditProfesionalController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
