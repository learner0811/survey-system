/**
 * 
 */

'use strict'
angular.module('indexService', []).factory('indexService',
		[ '$http', function($http) {
			var service = {};
			var urlBase = 'http://localhost:8080/api'			
			service.getAllSurvey = function(){
				var uri = '/survey';
				return $http.get(urlBase+uri, {cache : true});					
			}
			return service;			
		} ]);