function mostReply() {
    let option = $("#option").text("最多回复");
    $("#pagenum").val(1);
    $("div[name='post']").remove()
    more();
}

function recent() {
    $("#option").text("最近回复");
    $("#pagenum").val(1);
    $("div[name='post']").remove()
    more();
}

function newest() {
    $("#option").text("最新主题");
    $("#pagenum").val(1);
    $("div[name='post']").remove()
    more();
}

function mostView() {
    $("#option").text("最多点击");
    $("#pagenum").val(1);
    $("div[name='post']").remove()
    more();
}

function tag(e) {
    let tag = e.getAttribute("data-tag")
    $("#tag").val(tag);
    $("#pagenum").val(1);
    $(document).attr("title", tag);
    $("div[name='post']").remove()
    more();
}

function more() {
    let option = $("#option").text();
    let link = "/yunke/index/";
    if (option === "最新主题") {
        link += "latest";
        // orderBy = "latest";
    }
    if (option === "最多回复") {
        link += "mostreply";
        // orderBy = "mostreply";
    }
    if (option === "最近回复") {
        link += "recent";
        // orderBy = "recent/";
    }
    if (option === "最多点击") {
        link += "mostview";
        // orderBy = "mostview";
    }
    let tag = $("#tag").val();
    if (tag != null && tag !== "") {
        link += "/" + tag;
    }
    let pagenum = $("#pagenum").val();
    // let ps = $(".ps");
    $.get(link + "/" + pagenum, function (data) {
        postList = JSON.parse(data);
        $(postList).each(function (i, post) {
            //拼装html
            let li = "<div name='post' class=\"index-p uk-margin-top\">\n" +
                "                <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td width=\"60\" valign=\"top\" align=\"center\">\n" +
                "                            <a href=\"/yunke/u/" + post.authorId + "\" target=\"_blank\"><img src=\"" + post.authorAvatar + " \" alt=\"\"></a>\n" +
                "                        </td>\n" +
                "                        <td width=\"10\"></td>\n" +
                "                        <td width=\"auth\" valign=\"middle\">\n" +
                "                            <a href=\"/p/" + post.id + "\" class=\"uk-link-heading\" target=\"_blank\"><span\n" +
                "                             >" + post.title + "</span>\n" +
                "                            </a>\n" +
                "                            <div height=\"5px\"></div>\n" +
                "                            <span class=\"index-post-title-span uk-text-small uk-text-muted uk-align-right\"\n" +
                "                                  style=\"margin-bottom: 0\">\n" +
                "                            <img src=\"http://q9p1v1fsb.bkt.clouddn.com/tags.png\" alt=\"\"\n" +
                "                                 width=\"15\">\n" +
                "                            <span>" + post.tags + "</span>\n" +
                "                        </span>\n" +
                "                            <span class=\"uk-text-small uk-text-muted\">\n" +
                "                            <span>" + post.info + "</span>\n" +
                "                            <img src=\"http://q9p1v1fsb.bkt.clouddn.com/view.png\" alt=\"\" width=\"15\">\n" +
                "                            <span>" + post.viewCount + "</span>\n" +
                "                            <img src=\"http://q9p1v1fsb.bkt.clouddn.com/connent.png\" alt=\"\" width=\"15\">\n" +
                "                            <span>" + post.replyCount + "</span>\n" +
                "                        </span>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </div>\n" +
                "        </div>"
            $("#ps-list").append(li);
        });
        if (postList[0].presentPageNum === postList[0].pageMaxNum) {
            $("#more").text("已经没有更多了")
            return;
        }
        $("#pagenum").val(pagenum - 1 + 2);
    });
}