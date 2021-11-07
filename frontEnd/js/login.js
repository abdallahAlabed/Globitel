$(function () {

    $('.login-form').submit(function (event) {
        event.preventDefault();
        var userName = $('.login-form').find('input[name="userName"]').val();
        var password = $('.login-form').find('input[name="password"]').val();

        $.ajax({
            url: 'http://localhost:8080/login',
            type: 'POST',
            dataType: "JSON",
            data: { username: userName, pass: password }
        }).done(response => {
            if (response.role == "Admin") {
                window.location.href = "home.html"
            } else if (response.role == "user") {
                alert("you are not an admin ");
            } else
                alert("you dont have a user role");
        })
    });
})