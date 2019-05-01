angular.module("crudApp").controller('LoginController', LoginController);

    LoginController.$inject = ['$scope','$location','$cookieStore','$rootScope', 'UserService'];
    function LoginController($scope,$location,$cookieStore, $rootScope, UserService) {
    	
    	 
    	 $scope.user = {};
    	 
    	 $scope.login = function(){

    		 UserService.query({email: $scope.user.email, password: $scope.user.password}).$promise.then(function(response) {
	  
   			  if(response[0] != undefined){   
   				  		 $rootScope.logedUserName = response[0].firstName;
    				  	 $rootScope.logedUserID = response[0].userID;
    	    			 $cookieStore.put('user', response[0]);   	    			 
    	    			 $location.path('/');
    	    		 }else{
    	    			 $scope.errorMessage = 'Wrong Email or Password'; 
    	    		 }
    			  
    		  });

    	 }
       
    }
