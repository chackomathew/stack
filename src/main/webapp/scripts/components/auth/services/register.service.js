'use strict';

angular.module('zDPortalApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


