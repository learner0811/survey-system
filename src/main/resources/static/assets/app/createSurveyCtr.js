/**
 * 
 */
'use strict'

var module = angular.module('createSurveyCtr', []);
module.controller("createSurveyCtr", ["$scope", "createSurveyService", 'growl',
		function($scope, createSurveyService, growl) {
			// initialization model
			var survey = {
				id : "",
				category : {},
				name : "",				
				description : "",
				datecreate : "",
				questions : []
			};						
												
			
			$scope.survey = survey;
			$scope.questions = [];			
			$scope.categories = [];
			
			//load data
			createSurveyService.getAllCategories().then(function(response){				
				response.data.forEach(function(array){
					$scope.categories.push({idcategory : array.idcategory, name : array.name});					
				});				
				$scope.survey.category = $scope.categories[0];								
			});
						
			
			//view function
			$scope.add_survey = function(){
				$scope.survey.datecreate = new Date();
				$scope.survey.questions = $scope.questions;
				createSurveyService.saveSurvey($scope.survey).then(function(response){
					if (response.status == 200){
						resetSurvey();
						showMessage('success', 'Lưu bài khảo sát thành công');
					}
					else
						showMessage('error', 'Đã có lỗi xảy ra hãy thử lại');
				});
			}
			
			$scope.add_question = function() {				
				var question = {type : '12', choices : []};				
				$scope.questions.push(question);
			}
			
			$scope.add_answer = function($index){				
				var choice = {answer : ''};				
				$scope.questions[$index].choices.push(choice);				
			}
			
			$scope.remove_question = function($index){
				$scope.questions.splice($index, 1);				
			}
			
			$scope.remove_answer = function(x, y){								
				$scope.questions[x].choices.splice(y, 1);
			}
			
			$scope.change_quest = function($index){				
				var type = $scope.questions[$index].type;
				if (type == 12 || type == 13){					
					$scope.questions[$index].choices = [];
					$scope.questions[$index].name = '';
				}				
				else if (type == 14){
					delete $scope.questions[$index].choices;
					$scope.questions[$index].name = '';					
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
				$scope.survey.name = '';
				$scope.survey.questions = [];
				$scope.questions = [];				
			}
		} ]);
