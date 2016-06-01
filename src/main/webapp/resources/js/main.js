/**
 * Created by Serhii Levchynskyi on 06.05.2016.
 */

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/rest/items/",
        success: function (items) {
            $.each(items, function (i, item) {
                $("#mainTable").append(
                    '<tr>' +
                    '<td>' + item.id + '</td>' +
                    '<td>' + item.title + '</td>' +
                    '<td>' + item.description + '</td>' +
                    '<td>' + item.owner.email + '</td>' +
                    '</tr>'
                );
            });
        }
    });
});
