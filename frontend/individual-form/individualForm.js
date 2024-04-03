(function(angular) {
    'use strict';
  function IndividualFormController($http, individualService) {
    var ctrl = this;
    
    ctrl.user = {
        name: null,
        password: null,
        individualParent: null,
    }

    ctrl.save = async function(user) {
      ctrl.user.individualParent = individualService?.person?.id;
      await $http.post("http://localhost:8080/v1/", ctrl.user).then(function (response) {
        ctrl.updateList();
        ctrl.user = {
          name: null,
          password: null,
          individualParent: null,
        }
        individualService.onSelect(null)
      });
    }

  }
  
  angular.module('netdealApp').component('individualForm', {
    templateUrl: 'individual-form/individualForm.html',
    controller: IndividualFormController,
    bindings: {
      updateList: '&',
    },
  });
  })(window.angular);
