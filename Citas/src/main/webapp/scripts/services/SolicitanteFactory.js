angular.module('citas').factory('SolicitanteResource', function($resource){
    var resource = $resource('rest/solicitantes/:SolicitanteId',{SolicitanteId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});