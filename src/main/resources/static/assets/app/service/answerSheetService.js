/**
 * 
 */

'use strict'
angular.module('answerSheetService', []).factory('answerSheetService',
		[ '$http', function($http) {
			var service = {};
			var urlBase = 'http://localhost:8080/api'
			service.getAnswerSheetBySurveyId = function(paramId) {
				var uri = '/answersheets/survey/';
				return $http.get(urlBase + uri + paramId);
			}	
			service.saveAnswerSheet = function(answerSheet){
				var uri = '/answersheet';
				return $http.post(urlBase+uri, answerSheet);					
			}
			return service;			
		} ]);