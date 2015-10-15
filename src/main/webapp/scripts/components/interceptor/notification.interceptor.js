 'use strict';

angular.module('zDPortalApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-zDPortalApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-zDPortalApp-params')});
                }
                return response;
            }
        };
    });
