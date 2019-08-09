/**
 * 
 */

'use strict'
angular.module('surveyService', []).factory('surveyService',
		[ '$http', function($http) {
			var service = {};
			var urlBase = 'http://localhost:8080/api'
			service.getSurvey = function(paramId) {
				var uri = '/survey/';
				return $http.get(urlBase + uri + paramId);
			}	
			service.saveAnswerSheet = function(answerSheet){
				var uri = '/answersheet';
				return $http.post(urlBase+uri, answerSheet);					
			}
			return service;			
		} ]);