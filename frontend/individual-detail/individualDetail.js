(function(angular) {
    'use strict';
  function IndividualDetailController(IndividualService) {
    var ctrl = this;
  
    ctrl.onSelect = function(selectedIndividual) {
      if (ctrl.isSelected()) {
        IndividualService.onSelect({});
      } else {
        IndividualService.onSelect({...selectedIndividual});
      }
    };

    ctrl.isSelected = function() {
      return IndividualService.equals(ctrl?.individual?.id);
    };

  }
  
  angular.module('netdealApp').component('individualDetail', {
    templateUrl: 'individual-detail/individualDetail.html',
    controller: IndividualDetailController,
    bindings: {
      individual: '<',
    },
  });
  })(window.angular);
