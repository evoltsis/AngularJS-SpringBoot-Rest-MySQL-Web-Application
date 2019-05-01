angular.module("crudApp").controller('RegisterController', RegisterController);

    RegisterController.$inject = ['$scope','$location', '$rootScope','$cookieStore', 'UserService'];
    function RegisterController($scope,$location, $rootScope,$cookieStore, UserService) {
    	
    	$scope.register = function(){
    		UserService.query({email: $scope.user.email, password: $scope.user.password}).$promise.then(function(response) {
  			  	  
  			  if(response[0] == undefined){
  				  
	  				UserService.save($scope.user, function(responce) {
	  					 $rootScope.logedUserName = responce.firstName;
	  					 $rootScope.logedUserID = responce.userID;
	  					 $cookieStore.put('user',responce);   	    			 
    	    			 $location.path('/');	  					
	  				});
  	    			 
  	    		 }else{
  	    			 
  	    			 $scope.errorMessage = 'Email and password already exist'; 
  	    		 }
  			  
  		  });
    	}

    }


