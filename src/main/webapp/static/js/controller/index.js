var app = angular.module('StarShip', ['ngMaterial', 'angular-c3-simple']);
app.controller('indexController', function ($scope, $http, $interval) {
    var day = 60 * 60 * 24;
    $scope.addTime = 10;
    $scope.plotField = '.age';
    $scope.bodies = [];
    $scope.fields = {};

    setFields();

    function getBody(renew) {
        if ($scope.getBody != null)//if polling data already run
            return;

        var wasRenew = false;

        $scope.getBody = $interval(function () {
            var options = 'addTime=' + ($scope.addTime * day) + '&renew=' + (wasRenew ? false : renew);
            wasRenew = true;

            $http.post('api/body?' + options)
                .then(function (response) {
                    $scope.bodies.push(response.data);
                    plot();
                }, null);
        }, 1000);
    };

    function setFields() {

        var options = 'addTime=0&renew=false';
        $http.post('http://localhost:8080/api/body?' + options)
            .then(function (response) {
                $scope.fields = formatFields(objectToPaths(response.data));
            }, null);
    }

    function plot() {
        //map bodies
        var columns = $scope.bodies
            .map(function (x) {
                var expression = "x" + $scope.plotField;
                return eval(expression);
            });
        //add label
        var label = $scope.fields.filter(function (field) {
            return field.value == $scope.plotField
        })[0].text;
        columns.unshift(label);

        $scope.chart = c3.generate({
            bindto: '#chart',
            data: {
                columns: [
                    columns
                ]
            }
        });
    };

    $scope.start = function () {
        getBody(false);
    };
    $scope.stop = function () {
        $interval.cancel($scope.getBody);
        $scope.getBody = null;
    };

    $scope.renew = function () {
        $scope.stop();
        $scope.bodies = [];
        getBody(true);
    };

    $scope.getValues = function (body) {
        return $scope.bodies.map(function (body) {
            return eval('body' + $scope.plotField);
        });
    }
});