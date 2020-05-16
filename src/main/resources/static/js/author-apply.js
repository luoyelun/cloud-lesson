function authorApply() {
    let reason = $("#reason").val();
    if (reason === null || reason === "") {
        UIkit.notification({
            message: '请填写理由',
            status: 'danger',
            pos: 'bottom-center',
            timeout: 1000
        });
        return;
    }
    $.post("/yunke/u/authorApply", {
        "reason": reason
    }, function (response) {
        if (response.code === 200) {
            UIkit.notification({
                message: response.message,
                status: 'success',
                pos: 'bottom-center',
                timeout: 1000
            });
            $(function () {
                setTimeout(function () {
                    window.location.reload();
                }, 1000)
            });
        } else {
            UIkit.notification({
                message: response.message,
                status: 'danger',
                pos: 'bottom-center',
                timeout: 1000
            });
        }
    });
}

function setApplyInfo(e) {
    let applyId = e.getAttribute("data-apply-id");
    let applyReason = e.getAttribute("data-apply-reason");
    let applyUsername = e.getAttribute("data-apply-username");
    $("#apply-username").text(applyUsername);
    $("#apply-reason").text(applyReason);
    $("#confirm-btn").attr("data-apply-id", applyId);
}

function process(e) {
    let authorApplyId = e.getAttribute("data-apply-id");
    let status = $('input[name=apply-status]:checked').val();
    $.post("/yunke/admin/authorApplyManage/modifyAuthorApplyStatus", {
        "authorApplyId": authorApplyId,
        "status": status
    }, function (response) {
        if (response.code === 200) {
            UIkit.notification({
                message: response.message,
                status: 'success',
                pos: 'bottom-center',
                timeout: 1000
            });
            $(function () {
                setTimeout(function () {
                    window.location.reload();
                }, 1000)
            });
        } else {
            UIkit.notification({
                message: response.message,
                status: 'danger',
                pos: 'bottom-center',
                timeout: 1000
            });
        }
    });
}

function turnPage(action) {
    let pageNum = $("#page-info").attr("data-page-num");
    let maxPageNum = $("#page-info").attr("data-max-page-num");
    if (action === "next" && pageNum === maxPageNum) {
        UIkit.notification({
            message: "已经到最后了",
            status: 'danger',
            pos: 'bottom-center',
            timeout: 1000
        });
        return;
    }
    if (action === "previous" && pageNum === '1') {
        UIkit.notification({
            message: "已经是第一页了",
            status: 'danger',
            pos: 'bottom-center',
            timeout: 1000
        });
        return;
    }
    if (action === "next") {
        pageNum = pageNum - 1 + 2;
    }
    if (action === "previous") {
        pageNum = pageNum - 1;
    }
    $.get("/yunke/admin/authorApplyManage/" + pageNum, function (data) {
        applyList = JSON.parse(data);
        $("th[name='apply-info']").remove();
        $(applyList).each(function (i, apply) {
            let html = "<tr>\n" +
                "                    <th name=\"apply-info\">" + apply.accountUsername + "</th>\n" +
                "                    <th name=\"apply-info\">" + apply.createTime + "</th>\n" +
                "                    <th name=\"apply-info\">" + apply.status + "</th>\n" +
                "                    <th name=\"apply-info\">\n" +
                "                        <a href=\"#apply-info\" \n" +
                "                           data-apply-id=\"" + apply.id + "\"\n" +
                "                           data-apply-reason=\"" + apply.applyReason + "\"\n" +
                "                           onclick=\"setApplyInfo(this)\" uk-toggle>处理</a>\n" +
                "                    </th>\n" +
                "                </tr>";
            $("#apply-list").append(html)
        });
        $("#page-info").attr("data-page-num", applyList[0].pageNum);
        $("#page-info").attr("data-max-page-num", applyList[0].maxPageNum);
        $("#page-info").text("第" + applyList[0].pageNum + "页，共" + applyList[0].maxPageNum + "页");
    });
}