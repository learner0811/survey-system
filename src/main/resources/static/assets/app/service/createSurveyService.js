/**
 * 
 */

'use strict'
angular.module('createSurveyService', []).factory('createSurveyService',
		[ '$http', function($http) {
			var service = {};
			var urlBase = 'http://localhost:8080/api'
			service.getAllCategories = function() {
				var uri = '/categories';
				return $http.get(urlBase + uri);
			}
			service.saveSurvey = function(survey){
				var uri = '/survey';
				return $http.post(urlBase+uri, survey);					
			}
			return service;
		} ]);