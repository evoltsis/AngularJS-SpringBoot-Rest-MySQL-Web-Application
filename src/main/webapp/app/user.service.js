angular.module('crudApp').factory('UserService',UserService);

UserService.$inject = [ '$resource'];

function UserService($resource) {
	var resourceUrl = 'api/user';
	

	return $resource(resourceUrl, {}, {
		update: {method: 'POST'},
		save: {method: 'PUT'}
	});
	
	
}