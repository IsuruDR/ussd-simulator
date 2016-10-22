/**
 * Created by isuru on 10/22/16.
 */
'use strict';


angular.module('ussdPrompt').component('ussdPrompt', {
    templateUrl: 'components/ussd-prompt/ussd-prompt.template.html',

    controller: function PollForUssd ($http) {
        var self = this;

        $http.get('http://localhost:8080/ussd/poll').then(function (response) {
            self.message = response.data;
        });
    }

});