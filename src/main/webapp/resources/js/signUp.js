/**
 * Created by Serhii Levchynskyi on 13.05.2016.
 */
$(document).ready(function () {
    $("#signUp").click(function () {
        console.log("SignUp fired");
        var user = {
            email: $("#email").val(),
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            password: $("#pwd").val()
        };
        console.log(user);
        $.ajax({
            type: "POST",
            url: "/rest/users",
            data: JSON.stringify(user),
            contentType: "application/json",
            success: function (data) {
                console.log("item JSON: " + JSON.stringify(data));
                window.location.href = "http://localhost:8080/";

            }
        });
    });


});