/**
 * Created by isuru on 10/22/16.
 */
'use strict';


angular.module('ussdPrompt').component('ussdPrompt', {
    templateUrl: 'components/ussd-prompt/ussd-prompt.template.html',

    controller: function UssdPromptController($http, $scope, $timeout) {
        var self = this;
        $scope.$watch('inputVal', function (val) {
            if (val) {
                self.reply = val;
            }
        });

        var poll = function () {
            $timeout(function () {
                if (!$('#ussdPrompt').is(":visible")) {

                    $http.get('http://localhost:8080/ussd/poll').then(function (response) {
                        self.message = response.data;
                        if (response.data != '') {
                            $('#ussdPrompt').show();
                        }
                    });
                }
                poll();
            }, 1000);
        };
        poll();

        self.cancel = function cancel() {
            $('#ussdPrompt').hide();
        };

        self.ok = function ok() {

            var receivedData = self.message;
            var postData = {
                "inboundUSSDMessageRequest": {
                    "address": "tel:+" + receivedData.address,
                    "sessionID": "",
                    "shortCode": receivedData.shortCode,
                    "keyword": receivedData.keyword,
                    "inboundUSSDMessage": self.reply,
                    "clientCorrelator": receivedData.clientCorrelator,
                    "responseRequest": {
                        "notifyURL": receivedData.responseRequest.notifyURL,
                        "callbackData": receivedData.responseRequest.callbackData
                    },
                    "ussdAction": receivedData.ussdAction
                }
            };

            $http.post('http://localhost:8080/ussd/v1/updateIds', postData).then(function (response) {
                $('#ussdPrompt').hide();
            }, function errorCallback(response) {
                alert('Error occurred while sending response to IDS');

                $('#ussdPrompt').hide();
            });
        }
    }

});