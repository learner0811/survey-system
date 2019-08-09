/**
 * 
 */
'use strict'

var module = angular.module('answerSheetCtr', []);
module.controller("answerSheetCtr", [ "$scope", "answerSheetService", 'growl', '$location', '$window',
	function($scope, answerSheetService, growl, $location, $window) {
		var paramId = $location.search().id;
		$scope.id = paramId;	
		
		$scope.answerSheets = {};
		
		answerSheetService.getAnswerSheetBySurveyId(paramId).then(function(response){
			if (response.data.data == ''){				
				showMessage('info', 'Chưa có bài làm nào được thực hiện');
			}
			else{
				$scope.answerSheets = response.data.data;						
			}						
		});
		
		$scope.toAnswerSheetPdf = function (id){
			var landingUrl = "http://" + $window.location.host + "/answer_sheet_pdf/"+id;
			$window.location.href = landingUrl;
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
