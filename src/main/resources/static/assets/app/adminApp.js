/**
 * 
 */

'use strict'

var adminApp = angular.module('admin', [ 'ngRoute', 'adminCtr', 'adminService',
		'createSurveyCtr', 'createSurveyService', 'editSurveyService',
		'editSurveyCtr', 'loginCtr', 'loginService', 'registerService', 'registerCtr',
		'answerSheetCtr', 'answerSheetService' ,'angular-growl' ]);

adminApp.factory('AuthInterceptor', function($injector, $q, $location, growl) {			
	var requestInterceptor = {
		responseError : function(rejection){			
			if (rejection.status == 401 && rejection.config.url != 'views/partials/login.html'){
				$location.path('/login');				
				growl.error('Bạn phải Đăng nhập', {title : 'Error'});
			}
			return $q.reject(rejection);
		}
	};
	return requestInterceptor
});

adminApp.config(function($httpProvider) {	
	$httpProvider.interceptors.push('AuthInterceptor');
});

adminApp.config(function($routeProvider, $locationProvider, $httpProvider) {	
	$locationProvider.hashPrefix('');
	$routeProvider.when("/", {
		controller : "adminCtr",		
		templateUrl : "views/partials/mainAdmin.html",		
	})
	.when("/create", {
		controller : "createSurveyCtr",
		templateUrl : "views/partials/creatSurvey.html"
	}).when("/edit", {
		controller : "editSurveyCtr",
		templateUrl : "views/partials/editSurvey.html"
	})
	.when("/login", {
		controller : "loginCtr",
		templateUrl : "views/partials/login.html"
	})
	.when("/register", {
		controller : "registerCtr",
		templateUrl : "views/partials/register.html"
	})
	.when("/answersheet", {
		controller : "answerSheetCtr",
		templateUrl : "views/partials/answersheet.html"
	})
	.otherwise({
		redirect: '/login'
	});
		
});

adminApp.controller('commonCtr', function($scope, $rootScope, $http, $location, $templateCache){
	$scope.logout = function(){
		$http.defaults.headers.common['Authorization'] = '';
		$rootScope.username = '';		
		$templateCache.remove('views/partials/mainAdmin.html');
		console.log('logout');
		$location.path('/login');
	}
});

function showMessage(type, msg){
	if (type == 'error')
		growl.error(msg, {title : 'Error'});
	else if (type == 'success')
		growl.success(msg, {title : 'Success'});
	else if (type == 'warning')
		growl.warning(msg, {title : 'warning'});
	else if (type == 'info')
		growl.info(msg, {title : 'info'});
}