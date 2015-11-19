 'use strict';

 angular.module('stackPortalApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-stackPortalApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, {param: response.headers('X-stackPortalApp-params')});
                }
                return response;
            }
        };
    });
