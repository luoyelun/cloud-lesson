function avatarUpload() {
    var formdata = new FormData();
    formdata.append('fileName', $('#filename').get(0).files[0]);
    $.ajax({
        url: '/yunke/u/avatarupload', /*接口域名地址*/
        type: 'post',
        data: formdata,
        cache: false,
        contentType: false,
        processData: false,
        success: function (response) {
            if (response.code === 200) {
                window.location.reload();
            } else {
                UIkit.notification({
                    message: response.message,
                    status: 'danger',
                    pos: 'bottom-center',
                    timeout: 1000
                });
            }
        }
    });
}

function saveinfo() {
    let password = $("#password").val();
    let name = $("#name").val();
    let qq = $("#qq").val();
    let wechat = $("#wechat").val();
    $.ajax({
        type: "post",
        url: "/yunke/u/modifyAccountInfo",
        contentType: 'application/json',
        dataType: "json",
        data: JSON.stringify({
            "password": password,
            "name": name,
            "qq": qq,
            "wechat": wechat
        }),
        success: function (response) {
            if (response.code === 200) {
                window.location.reload();
            } else {
                UIkit.notification({
                    message: response.message,
                    status: 'danger',
                    pos: 'bottom-center',
                    timeout: 1000
                });
            }
        }
    });
}