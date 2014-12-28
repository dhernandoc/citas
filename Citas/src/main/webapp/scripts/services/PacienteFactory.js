angular.module('dameCita').factory('PacienteResource', function($resource){
    var resource = $resource('rest/pacientes/:PacienteId',{PacienteId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});