/**
 * 
 */

var module = angular.module('loginCtr', []);
module.controller("loginCtr", ["$scope", "loginService", 'growl', function($scope, loginService, growl){
	var accountDTO = {username : '', password : ''}
	$scope.accountDTO = accountDTO;
	
	$scope.login = function(accountDTO){		
		if (accountDTO.username == '' || accountDTO.password == ''){
			showMessage('error', 'Tài khoản hoặc mật khẩu không được để trống');			
		}
		else{
			loginService.login(accountDTO).then(function(response){
				console.log(response);
				if (response.data.messageType == 'error' && 
					response.data.message == "Bad credentials")
						showMessage('error', 'Tài khoản hoặc mật khẩu không đúng');
				else if (response.data.messageType == 'error')
					showMessage('error', response.data.message);
				else{
					var token = response.data.data;
					console.log(token);
				}
					
			});
		}
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