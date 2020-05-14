function fmImageUpload() {
    var formdata = new FormData();
    formdata.append('fileName', $('#filename').get(0).files[0]);
    $.ajax({
        url: '/yunke/admin/recommend/fmImageUpload', /*接口域名地址*/
        type: 'post',
        data: formdata,
        cache: false,
        contentType: false,
        processData: false,
        success: function (response) {
            if (response.code === 200) {
                $("#fm-image").attr("src", response.message);
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

function addRecommend() {
    let imgLink = $("#fm-image").attr("src");
    let title = $("#title").val();
    let description = $("#desc").val();
    let topicLink = $("#topic-link").val();
    if (imgLink === null || imgLink === "") {
        UIkit.notification({
            message: "未选择封面图",
            status: 'danger',
            pos: 'bottom-center',
            timeout: 1000
        });
        return;
    }
    if (title === null || title === "") {
        UIkit.notification({
            message: "未填写标题",
            status: 'danger',
            pos: 'bottom-center',
            timeout: 1000
        });
        return;
    }
    if (description === null || description === "") {
        UIkit.notification({
            message: "未填写描述",
            status: 'danger',
            pos: 'bottom-center',
            timeout: 1000
        });
        return;
    }
    if (topicLink === null || topicLink === "") {
        UIkit.notification({
            message: "未填写主题链接",
            status: 'danger',
            pos: 'bottom-center',
            timeout: 1000
        });
        return;
    }
    $.ajax({
        type: "post",
        url: "/yunke/admin/recommend/addRecommend",
        contentType: 'application/json',
        dataType: "json",
        data: JSON.stringify({
            "imgLink": imgLink,
            "title": title,
            "description": description,
            "topicLink": topicLink
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

function getRecommend(e) {
    let recommendId = e.getAttribute("data-cid");
    $.get("/yunke/admin/recommend/getRecommend/" + recommendId, function (data) {
        $("#modify-fm-image").attr("src", data.imgLink);
        $("#modify-title").val(data.title);
        $("#modify-desc").val(data.description);
        $("#modify-topic-link").val(data.topicLink);
        $("#modify-btn").attr("data-recommend-id", data.id);
    });
}

function modifyFmImageUpload() {
    var formdata = new FormData();
    formdata.append('fileName', $('#modify-fm-img').get(0).files[0]);
    $.ajax({
        url: '/yunke/admin/recommend/fmImageUpload', /*接口域名地址*/
        type: 'post',
        data: formdata,
        cache: false,
        contentType: false,
        processData: false,
        success: function (response) {
            if (response.code === 200) {
                $("#modify-fm-image").attr("src", response.message);
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

function modifyRecommend(e) {
    let recommendId = e.getAttribute("data-recommend-id");
    let imgLink = $("#modify-fm-image").attr("src");
    let title = $("#modify-title").val();
    let description = $("#modify-desc").val();
    let topicLink = $("#modify-topic-link").val();
    if (imgLink === null || imgLink === "") {
        UIkit.notification({
            message: "未选择封面图",
            status: 'danger',
            pos: 'bottom-center',
            timeout: 1000
        });
        return;
    }
    if (title === null || title === "") {
        UIkit.notification({
            message: "未填写标题",
            status: 'danger',
            pos: 'bottom-center',
            timeout: 1000
        });
        return;
    }
    if (description === null || description === "") {
        UIkit.notification({
            message: "未填写描述",
            status: 'danger',
            pos: 'bottom-center',
            timeout: 1000
        });
        return;
    }
    if (topicLink === null || topicLink === "") {
        UIkit.notification({
            message: "未填写主题链接",
            status: 'danger',
            pos: 'bottom-center',
            timeout: 1000
        });
        return;
    }
    $.ajax({
        type: "post",
        url: "/yunke/admin/recommend/modify",
        contentType: 'application/json',
        dataType: "json",
        data: JSON.stringify({
            "id": recommendId,
            "imgLink": imgLink,
            "title": title,
            "description": description,
            "topicLink": topicLink
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

function deleteRecommend(e) {
    let r = confirm("是否删除？")
    if (r === true) {
        let recommendId = e.getAttribute("data-cid");
        $.get("/yunke/admin/recommend/delete/" + recommendId, function (data) {
            if (data.code === 200) {
                $("#li-recommend-" + recommendId).remove();
                UIkit.notification({
                    message: data.message,
                    status: 'success',
                    pos: 'bottom-center',
                    timeout: 1000
                });
            } else {
                UIkit.notification({
                    message: data.message,
                    status: 'danger',
                    pos: 'bottom-center',
                    timeout: 1000
                });
            }
        });
    }
}