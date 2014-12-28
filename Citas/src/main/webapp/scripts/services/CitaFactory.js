angular.module('dameCita').factory('CitaResource', function($resource){
    var resource = $resource('rest/cita/:CitaId',{CitaId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});