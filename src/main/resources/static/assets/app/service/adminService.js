/**
 * 
 */

'use strict'
angular.module('adminService', []).factory('adminService',
		[ '$http', function($http) {
			var service = {};
			var urlBase = 'http://localhost:8080/api'			
			service.getAllSurvey = function(){
				var uri = '/survey';
				return $http.get(urlBase+uri);					
			}
			
			service.deleteSurvey = function(id){
				var uri = '/survey/';
				return $http.delete(urlBase+uri+id);					
			}
			return service;			
		} ]);