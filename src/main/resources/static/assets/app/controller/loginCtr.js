/**
 * 
 */

var module = angular.module('loginCtr', []);
module.controller("loginCtr", ["$scope", "loginService", 'growl', '$location', '$window' ,function($scope, loginService, growl, $location, $window){
	var accountDTO = {username : '', password : ''}	
	$scope.accountDTO = accountDTO;	
	$scope.login = function(accountDTO){				
		if (accountDTO.username == '' || accountDTO.password == ''){
			showMessage('error', 'Tài khoản hoặc mật khẩu không được để trống');			
		}
		else{			
			var login = loginService.login(accountDTO);
			var http = login.http;			
			login.promise.then(function(response){				
				if (response.data.messageType == 'error' && 
					response.data.message == "Bad credentials")
						showMessage('error', 'Tài khoản hoặc mật khẩu không đúng');
				else if (response.data.messageType == 'error')
					showMessage('error', response.data.message);
				else{
					var token = 'Bearer ' + response.data.data;					
					http.defaults.headers.common['Authorization'] = token;
					$window.sessionStorage.setItem("username", accountDTO.username);					
					$location.path('/');
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