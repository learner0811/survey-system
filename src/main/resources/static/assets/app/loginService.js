/**
 * 
 */

'use strict'
angular.module('loginService', []).factory('loginService',
		[ '$http', function($http) {
			var service = {};
			var urlBase = 'http://localhost:8080/api'
			/*service.getSurvey = function() {
				var uri = '/survey/29';
				return $http.get(urlBase + uri);
			}*/	
			service.login = function(userDTO){
				var uri = '/login';
				return $http.post(urlBase+uri, userDTO);					
			}
			return service;			
		} ]);