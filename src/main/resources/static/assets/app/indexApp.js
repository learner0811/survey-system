/**
 * 
 */

'use strict'

var IndexApp = angular.module('index', [ 'ngRoute', 'indexCtr', 'indexService',
		'surveyCtr', 'surveyService', 'angular-growl' ]);
IndexApp.config(function($routeProvider, $locationProvider) {
	$locationProvider.hashPrefix('');
	$routeProvider.when("/", {
		controller : "indexCtr",
		templateUrl : "views/partials/mainIndex.html"
	}).when("/survey", {
		controller : "surveyCtr",
		templateUrl : "views/partials/survey.html"
	})	
});