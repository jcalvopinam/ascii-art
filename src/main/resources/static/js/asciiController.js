/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */

app.controller('AsciiController', function ($scope, $http) {

    const pathArray = location.pathname.split('/');
    const appContext = pathArray[1];
    const baseUrl = location.protocol + '//' + location.host + '/' + appContext + '/';
    const urlImage = "^(?:http(s?)://)";
    const imageExtension = "\.(jpg|jpeg|png|gif)$";

    $scope.headingTitle = "Convert your text to ASCII ART";
    $scope.info = "Please enter some text or an image from URL!";
    $scope.textToConvert = "";
    $scope.responseArt = "";

    $scope.convert = function () {
        var url = new RegExp(urlImage);
        var image = new RegExp(imageExtension);
        if ($scope.textToConvert.match(url)) {
            if ($scope.textToConvert.match(image)) {
                $http({
                    method: "POST",
                    url: baseUrl + 'convertImage',
                    data: $scope.textToConvert
                }).then(function mySuccess(response) {
                    $scope.responseArt = response.data;
                    console.log("Success" + response.data);
                }, function myError(response) {
                    $scope.responseArt = "Error: " + response.status + " - Is not a valid image.";
                    console.log("Error" + response.statusText);
                });
            } else {
                $scope.responseArt = "Error: Is not a valid image.";
                console.log("Error" + $scope.responseArt);
            }
        } else {
            $http({
                method: "POST",
                url: baseUrl + 'convertText',
                data: $scope.textToConvert
            }).then(function mySuccess(response) {
                $scope.responseArt = response.data;
                console.log("Success" + response.data);
            }, function myError(response) {
                $scope.responseArt = "Error: " + response.status + " - " + response.statusText;
                console.log("Error" + response.statusText);
            });
        }

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
