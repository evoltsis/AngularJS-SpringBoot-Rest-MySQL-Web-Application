angular.module('crudApp').factory('NoteService', NoteService);

NoteService.$inject = [ '$resource' ];

function NoteService($resource) {
	var resourceUrl = 'api/note/:id';
	
	return $resource(resourceUrl, {}, {
		update: {method: 'POST'},
		save: {method: 'PUT'}
	});
	
	
	/*
	 return {
        getEmployees: function (url) {
       	 
            return $http.get(url) 
            .success(function (data) {
               
             
            });
             
        },
        updateEmployee: function (url,employee) {
            return $http.put(url)
            .success(function (data) {
               
             
            });
            
        },
        saveEmployee: function (url) {
            return $http.post(url)
            .success(function (data) {
               
             
            });
            
        },
        deleteEmployee: function (url) {
            return $http.delete(url)
            .success(function (data) {
               
             
            });
            
        }
	 };*/
}