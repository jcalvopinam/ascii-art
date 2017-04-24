/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */

app.controller('AsciiController', function ($scope, $http) {

    const pathArray = location.pathname.split('/');
    const appContext = pathArray[1];
    const baseUrl = location.protocol + '//' + location.host + '/' + appContext;
    const urlImage = "^(?:http(s?)://)";
    const imageExtension = "\.(jpg|jpeg|png|gif)$";

    $scope.headingTitle = "Convert your text to ASCII ART";
    $scope.info = "Please enter some text or an image from URL!";
    $scope.textToConvert = "";
    $scope.responseArt = "";
    $scope.fontList = "";

    $scope.convert = function () {
        var asciiDto = {};
        asciiDto["font"] = $scope.font;
        asciiDto["text"] = $scope.textToConvert;
        asciiDto["url"] = $scope.isValidUrl($scope.textToConvert);

        $http({
            method: "POST",
            url: baseUrl + '/generate',
            data: asciiDto
        }).then(function mySuccess(response) {
            $scope.responseArt = response.data;
            console.log("Success" + response.data);
        }, function myError(response) {
            $scope.responseArt = "Error: " + response.status + " - " + response.statusText;
            console.log("Error" + response.statusText);
        });
    };

    $scope.getFonts = function () {
        $http({
            method: "GET",
            url: baseUrl + '/fonts',
        }).then(function mySuccess(response) {
            $scope.fontList = response.data;
            console.log("Success" + response.data);
        }, function myError(response) {
            console.log("Error" + response.statusText);
        });
    };

    $scope.isValidUrl = function (textToConvert) {
        var url = new RegExp(urlImage);
        var image = new RegExp(imageExtension);
        return ((textToConvert.match(url)) && (textToConvert.match(image))) ? true : false;
    };

    $scope.cleanFields = function () {
        $scope.textToConvert = "";
        $scope.responseArt = "";
        $scope.font = '';
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

    $scope.getFonts();

});
