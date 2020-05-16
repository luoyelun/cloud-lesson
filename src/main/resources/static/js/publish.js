function publish() {
    let title = $("#title").val();
    let content = $("#content").val();
    let tags = $("#tags").val();
    let message = $("#publish-message")
    if (title === "" || title === null) {
        message.html("请填写标题");
        return;
    }
    if (tags === "" || tags === null) {
        message.html("请选择标签");
        return;
    }
    if (content === "" || content === null) {
        message.html("请填写内容");
        return;
    }
    let mark = $("#mark").val();
    if (mark === "1") {
        message.html("请点击上传视频");
        return;
    }
    let videoLink = $("#video-link").val();
    message.val("");
    message.html("<span uk-spinner></span>");
    $.ajax({
        type: "post",
        url: "/yunke/publish",
        contentType: 'application/json',
        dataType: "json",
        data: JSON.stringify({
            "title": title,
            "content": content,
            "tags": tags,
            "videoLink": videoLink
        }),
        success: function (response) {
            if (response.code === 200) {
                $("#publish-message").html(response.message);
                $(function () {
                    setTimeout(function () {
                        window.location.href = "/yunke";
                    }, 1000)
                });
            } else {
                $("#publish-message").html(response.message);
            }
        },
        error: function () {
            $("#publish-message").html("出现了不明问题，联系管理员alun96@outlook.com");
        }
    });
}

function setTag() {
    let tagArray = [];
    $("input[name=tag]:checked").each(function () {
        tagArray.push($(this).val())//向数组中添加元素
    });
    $("#tags").val(tagArray.join(","));
}