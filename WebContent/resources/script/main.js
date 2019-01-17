document.addEventListener('DOMContentLoaded', function () {
    var elems = document.querySelectorAll('.parallax');
    var paralaxInstance = M.Parallax.init(elems, 100);

});
$(document).ready(function () {
    $('.tabs').tabs();
    $('.modal').modal();
});


function sendNewEventForm() {

    var data = $('form').serializeFormJSON();
    var xhr = new XMLHttpRequest();
    var url = "addNewEvent";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    var data = JSON.stringify(data);
    xhr.send(data);

}

(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

