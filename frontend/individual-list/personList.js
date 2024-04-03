(function (angular) {
  "use strict";
  function IndividualListController($http, individualService) {
    var ctrl = this;

    ctrl.list = [];

    ctrl.updateList = async function () {
      await $http.get("http://localhost:8080/v1/").then(function (response) {
        ctrl.list = response.data;
      });
    }

    ctrl.updateList();

    ctrl.getIndividualSelected = function () {
      return individualService.individual;
    }
  }

  angular.module("netdealApp").component("individualList", {
    templateUrl: "individual-list/individualList.html",
    controller: IndividualListController,
  });
})(window.angular);
