/**
 * 
 */

var module = angular.module('adminCtr', []);
module.controller("adminCtr", ["$scope", "adminService", 'growl', function($scope, adminService, growl){
	
	adminService.getAllSurvey().then(function(response){
		$scope.surveys = response.data;
		console.log(response);
	});
	
	$scope.deleteSurvey = function(id){
		var result = confirm("Want to delete?");		
		if (result){
			adminService.deleteSurvey(id).then(function(response){
				console.log(response);
				if (response.data.messageType == 'success')
					showMessage('success', 'Xoá thành công');
				else
					showMessage('error', 'Đã có lỗi xảy ra');
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