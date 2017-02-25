/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */

app.controller('AsciiController', function ($scope, $http) {

    const pathArray = location.pathname.split('/');
    const appContext = pathArray[1];
    const baseUrl = location.protocol + '//' + location.host + '/' + appContext + '/';

    $scope.headingTitle = "ASCII ART PAGE";
    $scope.textToConvert = "";
    $scope.responseArt = "";

    $scope.convertText = function () {
        $http({
            method: "POST",
            url: baseUrl + 'convert',
            data: $scope.textToConvert
        }).then(function mySuccess(response) {
            $scope.responseArt = response.data;
            console.log("Success" + response.data);
        }, function myError(response) {
            console.log("Error" + response.statusText);
        });
    };

    $scope.cleanFields = function () {
        $scope.textToConvert = "";
        $scope.responseArt = "";
        $("#newText").focus();
    };

    $scope.downloadText = function () {
        if ($scope.responseArt !== "") {
            var blob = new Blob([$scope.responseArt], {type: "application/text;charset=utf-8;"});
            var downloadLink = angular.element('<a></a>');
            downloadLink.attr('href', window.URL.createObjectURL(blob));
            downloadLink.attr('download', 'ASCII-Art.txt');
            downloadLink[0].click();
        }
    };

});
