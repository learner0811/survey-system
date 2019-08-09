/**
 * 
 */


var module = angular.module('editSurveyCtr', []);
module.controller("editSurveyCtr", ["$scope", "editSurveyService", 'growl', "$location",
		function($scope, editSurveyService, growl, $location) {
			// initialization model
			var survey = {
				idsurvey : "",
				category : {},
				name : "",				
				description : "",
				datecreate : "",
				questions : []
			};						
												
			
			$scope.survey = survey;				
			$scope.categories = [];
			
			//load data
			editSurveyService.getAllCategories().then(function(response){				
				response.data.forEach(function(array){
					$scope.categories.push({idcategory : array.idcategory, name : array.name});					
				});				
				$scope.survey.category = $scope.categories[0];								
			});
									
			var url = $location.absUrl();
			var paramId = $location.search().id;		
			
			editSurveyService.getSurveyById(paramId).then(function(response){
				if (response.data != '')
					$scope.survey = response.data;				
			});
			
			//view function
			$scope.add_survey = function(){		
				//validate input
				if (!$scope.formValid){
					showMessage('error', "Các ô nhập không được để trống");
					return;
				}
				
				if ($scope.survey.questions.length == 0){
					showMessage('error', "Không có câu trả lời nào được tạo");
					return;
				}
				
				var questions = $scope.survey.questions;				
				for (let i = 0; i < questions.length; i++){					
					if (questions[i].type == 12){
						if (questions[i].choices.length == 0){
							showMessage('error', "Câu hỏi trắc nghiệm phải có ít nhất 1 lựa chọn");
							return;
						}
					}
					else if (questions[i].type == 13){
						if (questions[i].choices.length == 0){
							showMessage('error', "Câu hỏi nhiều lựa chọn phải có ít nhất 1 lựa chọn");
							return;
						}
					}					
				}
				
				editSurveyService.editSurvey($scope.survey).then(function(response){
					if (response.status == 200){						
						showMessage('success', 'Lưu bài khảo sát thành công');
						resetSurvey();
					}
					else
						showMessage('error', 'Đã có lỗi xảy ra hãy thử lại');
				});
			}
			
			$scope.add_question = function() {				
				var question = {type : '12', choices : []};				
				$scope.survey.questions.push(question);
			}
			
			$scope.add_answer = function($index){				
				var choice = {answer : ''};				
				$scope.survey.questions[$index].choices.push(choice);				
			}
			
			$scope.remove_question = function($index){
				$scope.survey.questions.splice($index, 1);				
			}
			
			$scope.remove_answer = function(x, y){								
				$scope.survey.questions[x].choices.splice(y, 1);
			}
			
			$scope.change_quest = function($index){				
				var type = $scope.survey.questions[$index].type;
				if (type == 12 || type == 13){					
					$scope.survey.questions[$index].choices = [];
					$scope.survey.questions[$index].name = '';
				}				
				else if (type == 14){
					delete $scope.survey.questions[$index].choices;
					$scope.survey.questions[$index].name = '';					
				}
			}
			
			//function
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
			
			function resetSurvey(){
				editSurveyService.getSurveyById(paramId).then(function(response){					
					$scope.survey = response.data;				
				});			
			}
		} ]);