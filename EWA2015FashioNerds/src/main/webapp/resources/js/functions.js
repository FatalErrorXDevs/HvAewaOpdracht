$(function () {
    $('.confirm-delete').click(function () {
        return window.confirm("Are you sure you want to delete '" + $(this).attr("data-name") + "'?");
    });
});

$.extend({
    password: function (length, special) {
        var iteration = 0;
        var password = "";
        var randomNumber;
        if (special === undefined) {
            var special = false;
        }
        while (iteration < length) {
            randomNumber = (Math.floor((Math.random() * 100)) % 94) + 33;
            if (!special) {
                if ((randomNumber >= 33) && (randomNumber <= 47)) {
                    continue;
                }
                if ((randomNumber >= 58) && (randomNumber <= 64)) {
                    continue;
                }
                if ((randomNumber >= 91) && (randomNumber <= 96)) {
                    continue;
                }
                if ((randomNumber >= 123) && (randomNumber <= 126)) {
                    continue;
                }
            }
            iteration++;
            password += String.fromCharCode(randomNumber);
        }
        return password;
    }
});

function generateCode(input)
{
    $(input).val($.password(12, true));
}