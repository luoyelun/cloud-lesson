function reply(e) {
    let targetId = e.getAttribute("data-id")
    let targetName = e.getAttribute("data-name")
    $("#alert").remove();
    let alert = "<div class=\"uk-alert-primary topic-comment-reply\" id=\"alert\" uk-alert>\n" +
        "                <p>回复：" + targetName + "</p>\n" +
        "                <a class=\"uk-alert-close\" uk-close></a>\n" +
        "                <input type=\"text\" id=\"reply-id\" value=\" " + targetId + "\" hidden=\"hidden\">\n" +
        "            </div>"
    $("#comment-main").prepend(alert);
}


function replySubmit() {
    let replyContent = $("#comment-content").val();
    if (replyContent === "" || replyContent === null) {
        UIkit.notification({
            message: '请填写回复内容',
            status: 'warning',
            pos: 'bottom-center',
            timeout: 2000
        });
        return;
    }
    let topicId = $("#topic-id").val();
    let targetId = $("#reply-id").val();
    $.ajax({
        type: "post",
        url: "/yunke/comment",
        contentType: 'application/json',
        dataType: "json",
        data: JSON.stringify({
            "topicId": topicId,
            "content": replyContent,
            "parentId": targetId
        }),
        success: function (response) {
            if (response.code === 200) {
                UIkit.notification({
                    message: response.message,
                    status: 'success',
                    pos: 'bottom-center',
                    timeout: 1000
                });
                $(function () {
                    setTimeout(function () {
                        location.reload();
                    }, 1000)
                })
            } else {
                UIkit.notification({
                    message: response.message,
                    status: 'danger',
                    pos: 'bottom-center',
                    timeout: 1000
                });
            }
        },
    });
}

function attention(e) {
    //获得主题id
    let topicId = e.getAttribute("data-topic-id");
    let type = e.getAttribute("data-type");
    let link;
    //添加关注
    if (type === "0") {
        link = "/yunke/addAttention";
    }
    //取消关注
    if (type === "1") {
        link = "/yunke/removeAttention";
    }
    $.ajax({
        type: "post",
        url: link,
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({
            "tid": topicId
        }),
        success: function (response) {
            //添加关注
            if (response.code === 200) {
                let attention = $("#attention");
                attention.text("取消关注");
                attention.attr("title", "点击取消关注");
                attention.attr("data-type", "1");
                return;
            }
            if (response.code === 201) {
                UIkit.notification({
                    message: response.message,
                    status: 'danger',
                    pos: 'bottom-center',
                    timeout: 1000
                });
                return;
            }
            //取消关注
            if (response.code === 202) {
                let attention = $("#attention");
                attention.text("关注");
                attention.attr("title", "点击关注");
                attention.attr("data-type", "0");
                return;
            }
            if (response.code === 203) {
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