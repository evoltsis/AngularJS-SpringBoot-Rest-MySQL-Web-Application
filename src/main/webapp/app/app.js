var app =angular.module("crudApp", ['ngRoute', 'ngCookies','ngResource']).config(config).run(run);

config.$inject = ['$routeProvider', '$locationProvider'];
function config($routeProvider, $locationProvider) {
$locationProvider.hashPrefix('');
$routeProvider
    .when('/', {
        controller: 'HomeController',
        templateUrl: 'app/home/home.view.html',
        controllerAs: 'vm'
    })

    .when('/login', {
        controller: 'LoginController',
        templateUrl: 'app/login/login.view.html',
        controllerAs: 'vm'
    })

    .when('/register', {
        controller: 'RegisterController',
        templateUrl: 'app/register/register.view.html',
        controllerAs: 'vm'
    })

    .otherwise({ redirectTo: '/login' });

}

run.$inject = ['$rootScope', '$location', '$cookies','$cookieStore'];
function run($rootScope, $location, $cookies, $cookieStore) {
    // keep user logged in after page refresh	
	if($cookieStore.get('user')!= undefined){			
		$rootScope.logedUserName = $cookieStore.get('user').firstName;
		$rootScope.logedUserID = $cookieStore.get('user').userID;
		$location.path('/');
	}

    $rootScope.$on('$locationChangeStart', function () {    	  	
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
        var loggedIn = ($cookieStore.get('user')!=undefined);         
        if (restrictedPage && !loggedIn) {
            $location.path('/login');
        }
    });
}
