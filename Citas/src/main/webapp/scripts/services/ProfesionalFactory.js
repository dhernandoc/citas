angular.module('dameCita').factory('ProfesionalResource', function($resource){
    var resource = $resource('rest/profesionals/:ProfesionalId',{ProfesionalId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});