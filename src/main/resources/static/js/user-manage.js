function setAccountInfo(e) {
    let status = e.getAttribute("data-status");
    let role = e.getAttribute("data-role");
    if (status === "正常登录") {
        $('input:radio[name=account-info-status]').eq(0).attr('checked', true);
    } else {
        $('input:radio[name=account-info-status]').eq(1).attr('checked', true);
    }
    if (role === "普通用户") {
        $('input:radio[name=account-info-role]').eq(0).attr('checked', true);
    } else {
        $('input:radio[name=account-info-role]').eq(1).attr('checked', true);
    }
    let accountId = e.getAttribute("data-account-id");
    $("#btn-save").attr("data-account-id", accountId);
}

function saveAccountInfo(e) {
    let status = $('input[name=account-info-status]:checked').val();
    let role = $('input[name=account-info-role]:checked').val();
    let accountId = e.getAttribute("data-account-id");
    let params = {
        "status": status,
        "role": role,
        "accountId": accountId
    }
    $.post("/yunke/admin/user/modify", params, function (response) {
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
    $.get("/yunke/admin/userManage/" + pageNum, function (data) {
        accountList = JSON.parse(data);
        $("th[name='accountInfo']").remove();
        $(accountList).each(function (i, account) {
            let html = "<tr>" +
                "<th name=\"accountInfo\">" + account.id + "</th>\n" +
                "                <th name=\"accountInfo\">\n" +
                "                    <a href=\"/yunke/u/" + account.id + "\" target=\"_blank\">" + account.username + "</a>\n" +
                "                </th>\n" +
                "                <th name=\"accountInfo\">" + account.name + "</th>\n" +
                "                <th name=\"accountInfo\">" + account.createTime + "</th>\n" +
                "                <th name=\"accountInfo\">" + account.qq + "</th>\n" +
                "                <th name=\"accountInfo\">" + account.wechat + "</th>\n" +
                "                <th name=\"accountInfo\">" + account.role + "</th>\n" +
                "                <th name=\"accountInfo\">" + account.status + "</th>\n" +
                "                <th name=\"accountInfo\" data-account-id=\"" + account.id + "\">\n" +
                "                    <a href=\"#account-info\" data-status=\"" + account.status + "\" data-role=\"" + account.role + "\"\n" +
                "                       data-account-id=\"" + account.id + "\"\n" +
                "                       onclick=\"setAccountInfo(this)\" uk-toggle>操作</a>\n" +
                "                </th>" +
                "</tr>";
            $("#account-list").append(html)
        });
        $("#page-info").attr("data-page-num", accountList[0].pageNum);
        $("#page-info").attr("data-max-page-num", accountList[0].maxPageNum);
        $("#page-info").text("第" + accountList[0].pageNum + "页，共" + accountList[0].maxPageNum + "页");
    });
}

function searchAccount() {
    let keyword = $("#keyword").val();
    if (keyword === "" || keyword === null) {
        window.location.reload();
    }
    $.get("/yunke/admin/userManage/search/" + keyword, function (data) {
        accountList = JSON.parse(data);
        $("th[name='accountInfo']").remove();
        $("#page-change").remove();
        $(accountList).each(function (i, account) {
            let html = "<tr>" +
                "<th name=\"accountInfo\">" + account.id + "</th>\n" +
                "                <th name=\"accountInfo\">\n" +
                "                    <a href=\"/yunke/u/" + account.id + "\" target=\"_blank\">" + account.username + "</a>\n" +
                "                </th>\n" +
                "                <th name=\"accountInfo\">" + account.name + "</th>\n" +
                "                <th name=\"accountInfo\">" + account.createTime + "</th>\n" +
                "                <th name=\"accountInfo\">" + account.qq + "</th>\n" +
                "                <th name=\"accountInfo\">" + account.wechat + "</th>\n" +
                "                <th name=\"accountInfo\">" + account.role + "</th>\n" +
                "                <th name=\"accountInfo\">" + account.status + "</th>\n" +
                "                <th name=\"accountInfo\" data-account-id=\"" + account.id + "\">\n" +
                "                    <a href=\"#account-info\" data-status=\"" + account.status + "\" data-role=\"" + account.role + "\"\n" +
                "                       data-account-id=\"" + account.id + "\"\n" +
                "                       onclick=\"setAccountInfo(this)\" uk-toggle>操作</a>\n" +
                "                </th>" +
                "</tr>";
            $("#account-list").append(html)
        });
    });
}