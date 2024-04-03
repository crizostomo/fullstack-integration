(function(angular) {
    'use strict';
    angular.module('netdealApp').service('individualService', function($http) {
        this.individual = null;
        
        this.onSelect = function (individual) {
            this.individual = individual;
        }

        this.equals = function (id) {
            return this?.individual?.id === id
        }

      });
  })(window.angular);


