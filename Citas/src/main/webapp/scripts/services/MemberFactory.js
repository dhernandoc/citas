angular.module('citas').factory('MemberResource', function($resource){
    var resource = $resource('rest/forge/members/:MemberId',{MemberId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});