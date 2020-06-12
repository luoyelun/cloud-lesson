function search(e) {
    if ($("#more").text() === "已经没有更多了") {
        return;
    }
    let keyword = e.getAttribute("data-keyword");
    let pageNum = e.getAttribute("data-pagenum");
    $.get("/yunke/search/bypagenum?keyword=" + keyword + "&pageNum=" + pageNum, function (data) {
        topicList = JSON.parse(data);
        $(topicList).each(function (i, topic) {
//拼装html
            let li = "<div name='topic' class=\"index-p uk-margin-top\">\n" +
                "                <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td width=\"60\" valign=\"top\" align=\"center\">\n" +
                "                            <a href=\"/yunke/u/" + topic.authorId + "\" target=\"_blank\"><img src=\"" + topic.authorAvatar + " \" alt=\"\"></a>\n" +
                "                        </td>\n" +
                "                        <td width=\"10\"></td>\n" +
                "                        <td width=\"auth\" valign=\"middle\">\n" +
                "                            <a href=\"/yunke/t/" + topic.id + "\" class=\"uk-link-heading\" target=\"_blank\"><span\n" +
                "                             >" + topic.title + "</span>\n" +
                "                            </a>\n" +
                "                            <div height=\"5px\"></div>\n" +
                "                            <span class=\"index-topic-title-span uk-text-small uk-text-muted uk-align-right\"\n" +
                "                                  style=\"margin-bottom: 0\">\n" +
                "                            <img src=\"http://qbppo2eal.bkt.clouddn.com/tags.png\" alt=\"\"\n" +
                "                                 width=\"15\">\n" +
                "                            <span>" + topic.tags + "</span>\n" +
                "                        </span>\n" +
                "                            <span class=\"uk-text-small uk-text-muted\">\n" +
                "                            <span>" + topic.info + "</span>\n" +
                "                            <img src=\"http://qbppo2eal.bkt.clouddn.com/view.png\" alt=\"\" width=\"15\">\n" +
                "                            <span>" + topic.viewCount + "</span>\n" +
                "                            <img src=\"http://qbppo2eal.bkt.clouddn.com/connent.png\" alt=\"\" width=\"15\">\n" +
                "                            <span>" + topic.replyCount + "</span>\n" +
                "                        </span>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </div>\n" +
                "        </div>"
            $("#ps-list").append(li);
        });
        if (topicList[0].presentPageNum === topicList[0].pageMaxNum) {
            $("#more").text("已经没有更多了")
            return;
        }
        $("#more").attr("data-pagenum", pageNum - 1 + 2);
    });
}