/**
 * 
 */

'use strict'
angular.module('registerService', []).factory('registerService',
		[ '$http', function($http) {
			var service = {};
			var urlBase = 'http://localhost:8080/api'
			service.getPermissions = function() {
				var uri = '/permissions';
				return $http.get(urlBase + uri);
			}	
			service.register = function(user){
				var uri = '/register';
				return $http.post(urlBase+uri, user);					
			}
			return service;			
		} ]);