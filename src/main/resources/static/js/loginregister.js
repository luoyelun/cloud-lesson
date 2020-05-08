function registera() {
    let registerUsername = $("#register-username").val();
    let registerPassword = $("#register-password").val();
    let name = $("#name").val();
    let qq = $("#qq").val();
    let wechat = $("#wechat").val();
    let captcha = $("#captcha").val();
    let i = $.inArray(" ", registerUsername + registerPassword + name + qq + wechat);
    if (i > 0) {
        $("#message").html("注册信息不能含有空格");
    } else {
        $("#message").html("<div uk-spinner></div>");
        $.ajax({
            type: "post",
            url: "/yunke/register",
            contentType: 'application/json',
            data: JSON.stringify({
                "username": registerUsername,
                "password": registerPassword,
                "name": name,
                "qq": qq,
                "wechat": wechat,
                "captcha": captcha
            }),
            success: function (response) {
                if (response.code === 200) {
                    $("#login-message").html(response.message);
                    $("#message").html(response.message);
                } else {
                    $("#message").html(response.message);
                }
            },
            error: function () {
                $("#message").html("出现了不明问题，联系管理员alun96@outlook.com");
            },
            dataType: "json"
        });
    }
}

function login() {
    let username = $("#username").val();
    let password = $("#password").val();
    let rememberMe = $('#rememberMe').prop('checked');
    let i = $.inArray(" ", username + password);
    if (i > 0) {
        $("#login-message").html("登录信息错误");
    } else if (username === "" || username === null || password === "" || password === null) {
        $("#login-message").html("账号密码不能为空");
    } else {
        $("#login-message").html("<div uk-spinner></div>");
        $.ajax({
            type: "post",
            url: "/yunke/login/form",
            contentType: "application/json",
            data: JSON.stringify({
                "username": username,
                "password": password,
                "rememberMe": rememberMe
            }),
            success: function (response) {
                if (response.code === 204) {
                    window.location.hrer = "/yunke/";
                }
                if (response.code === 205) {
                    $("#login-message").html(response.message);
                }
            },
            ERROR: function () {
                $("#message").html("出现了不明问题，联系管理员alun96@outlook.com");
            },
            dataType: "json"
        });
    }
}

function sendMail() {
    let mail = $("#register-username").val();
    $("#message").html("<div class='uk-align-center' uk-spinner></div>");
    $.ajax({
        type: "get",
        url: "/yunke/register/sendMail?mail=" + mail,
        contentType: "application/json",
        success: function (response) {
            if (response.code === 200) {
                $("#message").html("已发送");
            } else {
                $("#message").html(response.message);
            }
        },
        dataType: "json"
    });
}