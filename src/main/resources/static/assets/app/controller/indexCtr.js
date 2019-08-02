/**
 * 
 */

var module = angular.module('indexCtr', []);
module.controller("indexCtr", ["$scope", "indexService", 'growl', function($scope, indexService, growl){
	
	indexService.getAllSurvey().then(function(response){
		$scope.surveys = response.data;
		console.log(response);
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
}]);