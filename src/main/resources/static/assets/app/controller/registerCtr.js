'use strict'

var module = angular.module('registerCtr', []);
module.controller("registerCtr", [ "$scope", "registerService", 'growl',
		function($scope, registerService, growl) {

			$scope.user = {
				gender : 'male',
				permissionDTO : [],
				permission : {}
			};

			registerService.getPermissions().then(function(response) {
				var listPermissions = response.data.data;
				listPermissions.forEach(function(item) {
					item.value = item.code;
				});
				$scope.listPermissions = listPermissions;
				$(function() {
					$('.chosen-select').chosen();
					$('.chosen-select-deselect').chosen({
						allow_single_deselect : true
					});
				});
			});
			
			$scope.register = function(){				
				if ($scope.user.account.confirm_password != $scope.user.account.password){
					showMessage('error', 'Mật khẩu xác nhận không khớp');					
				}
				
				var listPermission = ''; 
				var listTemp = $scope.user.permissionDTO;
				var size = listTemp.length;
				listTemp.forEach(function(item, index){
					if (index != size-1)
						listPermission += item + ';';
					else
						listPermission += item;					
				});
				$scope.user.permission.listpermission = listPermission;	
				
				registerService.register($scope.user).then(function(response){
					console.log(response);
				});
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
		} ]);