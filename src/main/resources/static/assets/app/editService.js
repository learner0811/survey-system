/**
 * 
 */

'use strict'
angular.module('editSurveyService', []).factory('editSurveyService',
		[ '$http', function($http) {
			var service = {};
			var urlBase = 'http://localhost:8080/api'
			service.getAllCategories = function() {
				var uri = '/categories';
				return $http.get(urlBase + uri);
			}
			service.editSurvey = function(survey){
				var uri = '/survey';
				return $http.put(urlBase+uri, survey);					
			}
			service.getSurveyById = function(id){
				var uri = '/survey/';
				return $http.get(urlBase+uri+id);
			}			
			return service;
		} ]);