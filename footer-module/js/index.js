var disAll = $(".common-footer-module li");

//初始状态
disAll.eq(0).addClass("active")
$("body").css('background', disAll.eq(0).attr("data-color"));

$(".footer-items li").click(function () {
    dis = $(this);
    //修改body背景颜色
    var color = dis.attr("data-color");
    $("body").css('background', color);

    //控制wave的移动
    var index = dis.index();
    var footer_items_gap = ($(".common-footer-module").width() - 20 - 60 * 5) / 4
    var left = (-27 + index * (60 + footer_items_gap)) / 16

    //控制图标的上移下移
    for(var i = 0; i < disAll.length; i++){
        if (dis.attr("data-color") != disAll.eq(i).attr("data-color"))
            disAll.eq(i).removeClass("active");
        else{
            dis.delay(80).queue(function() {
                dis.addClass('active').dequeue();
            });
        }
    }
    $("#wave").css("left", left + "rem");
})
//nimei  de //#endregion  /fdsaf  、、发的萨芬撒