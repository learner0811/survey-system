/**
 * 
 */
'use strict'

var module = angular.module('surveyCtr', []);
module.controller("surveyCtr", [ "$scope", "surveyService", 'growl', '$location',
		function($scope, surveyService, growl, $location) {
			$scope.survey = {};			
			$scope.sendDisable = false;
			var answer_sheet = {
				participant : {
					email : '',
					name : '',
					phone : ''
				},
				answers : [],
				datecreate : ''
			};										
			
			$scope.answer_sheet = answer_sheet;
			
			var getQueryString = function ( field, url ) {
				var href = url ? url : window.location.href;
				var reg = new RegExp( '[?&]' + field + '=([^&#]*)', 'i' );
				var string = reg.exec(href);
				return string ? string[1] : null;
			};
				
			var url = $location.absUrl();
			var paramId = getQueryString('id', url);				
			
			surveyService.getSurvey(paramId).then(function(response) {
				$scope.survey = response.data;
				if (response.data != ''){
					response.data.questions.forEach(function(item){
						var answer = {answer : '', question : item, tempAnswer : []};
						$scope.answer_sheet.answers.push(answer);					
					});								
				}
				else
					$scope.sendDisable = true;
				$scope.answer_sheet.survey = response.data;
			}).catch(function(error){
				console.log(error);
			});
									
			$scope.save_answersheet = function(){
				$scope.answer_sheet.answers.forEach(function(x){
					if (x.question.type == '13'){
						var answer = '';							
						x.tempAnswer.forEach(function(y){
							if (y != '')
								answer += y +';';							
						});
						x.answer = answer;
					}
					else
						x.answer =x.tempAnswer;
				});	
				$scope.answer_sheet.datecreate = new Date();
				console.log($scope.answer_sheet);
				surveyService.saveAnswerSheet($scope.answer_sheet);
			}
		} ]);
