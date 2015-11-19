'use strict';

angular.module('stackPortalApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


