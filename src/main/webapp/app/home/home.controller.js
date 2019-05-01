angular.module("crudApp").controller("HomeController", HomeController);

HomeController.inject = [ '$scope','$rootScope','$cookieStore','$location', 'NoteService' ];

function HomeController($scope,$rootScope,$cookieStore,$location, NoteService) {
	
	$scope.toDoList = NoteService.query({userId:  $rootScope.logedUserID});

	$scope.note = {};
	$scope.username = $rootScope.logedUserName;
	$scope.note.userId = $rootScope.logedUserID;
	
	$scope.buttonText="Add Note";
	
	$scope.saveNote = function() {
		$scope.note.userId =  $rootScope.logedUserID;
		if ($scope.note.id !== undefined) {		
			NoteService.update($scope.note, function() {
				$scope.toDoList = NoteService.query({userId: $rootScope.logedUserID});
				$scope.note = {};
				$scope.buttonText="Add Note";
			});
		} else {
			NoteService.save($scope.note, function() {
				$scope.toDoList = NoteService.query({userId: $rootScope.logedUserID});
				$scope.note = {};
			});
		}
	}
	
	$scope.logOut = function(){
		 $rootScope.logedUserID={};
		 $cookieStore.remove('user');
		 $location.path('/login');		
	}

	$scope.updateNoteInit = function(note) {
		$scope.buttonText="Update";
		$scope.note = note;
	}

	$scope.deleteNote = function(note) {
		note.$delete({id: note.id}, function() {
			$scope.toDoList = NoteService.query({userId: $rootScope.logedUserID});
		});
	}
	
	
}