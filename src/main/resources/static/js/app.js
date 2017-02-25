/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */

var app = angular.module('asciiApp', ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            redirectTo: "index.html"
        });
});