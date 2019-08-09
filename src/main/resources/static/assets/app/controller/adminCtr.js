/**
 * 
 */

var module = angular.module('adminCtr', []);
module.controller("adminCtr", ["$scope", "adminService", 'growl', '$window', '$rootScope', '$location', function($scope, adminService, growl, $window, $rootScope, $location){
	
	var username = $window.sessionStorage.getItem('username');
	$rootScope.username = username;
	adminService.getAllSurvey().then(function(response){
		$scope.surveys = response.data;		
	});
		
	$scope.deleteSurvey = function(id){
		var result = confirm("Want to delete?");		
		if (result){
			adminService.deleteSurvey(id).then(function(response){				
				if (response.data.messageType == 'success'){
					showMessage('success', 'Xoá thành công');
					
					//update data
					adminService.getAllSurvey().then(function(response){
						$scope.surveys = response.data;		
					});
				}
				else
					showMessage('error', 'Đã có lỗi xảy ra');
			}).catch(function(e){
				showMessage('error', 'Đã có lỗi xảy ra');
				console.log(e);
			});			
		}	
		else
			console.log('huy xoa');
	}
	
	function removeSurveyView(id){
		
	}
	
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