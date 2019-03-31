const ap = new APlayer({
    container: document.getElementById('aplayer'),
    fixed: true,
    lrcType: 1,
    audio: [{
        name: 'Trouble I\'m In',
        artist: 'twinbed',
        url: 'aa.mp3',
        theme: '#666666',
        cover: 'zz.jpg',
        lrc: '[00:00.00]Trouble I\'m In \n' +
            '[00:25.52]I wanna feel your touch (我想要去感受你的触摸)\n' +
            '[00:32.85]It\'s burning me like an ember (我就像余烬在暗火焚烧)\n' +
            '[00:40.37]Pretending is not enough (表面平静，却无法遮掩心中炙烫)\n' +
            '[00:47.97]I wanna feel us together (我想和你一起燃烧成灰烬，飘舞)\n' +
            '[00:51.17]So I\'m giving in (所以我心屈服)\n' +
            '[01:02.50]So I\'m giving in (所以我心屈服)\n' +
            '[01:09.55]To the trouble I\'m in (陷入苦思冥想) \n' +
            '[01:17.42]So I\'m giving in (所以我心屈服)\n' +
            '[01:24.41]To the trouble I\'m in (陷入甜蜜烦恼)\n' +
            '[01:32.01]To the trouble I\'m in (陷入甜蜜烦恼)\n' +
            '[01:42.39]You are you are  (你是你是)\n' +
            '[01:46.65]My favorite medicine (我的灵丹解药)\n' +
            '[01:49.90]You are you are (你是你是)\n' +
            '[01:54.09]You\'re where the edge began (你就是那灯火阑珊处)\n' +
            '[01:57.35]You are you are (你是你是) \n' +
            '[02:01.53]Just one last time again (你就是我的苦思冥想)\n' +
            '[02:04.84]You are you are (你是你是)\n' +
            '[02:09.06]You are the trouble I\'m in (陷入甜蜜烦恼)\n' +
            '[02:16.63]You are the trouble I\'m in (陷入甜蜜烦恼)\n' +
            '[02:24.08]You are the trouble I\'m in (陷入甜蜜烦恼)\n' +
            '[02:27.31]You are you are (你是你是)\n' +
            '[02:31.66]My favorite medicine (我的灵丹解药)\n' +
            '[02:34.87]You are you are (你是你是)\n' +
            '[02:39.11]You\'re where the edge began (你就是那灯火阑珊处)\n' +
            '[02:42.36]You are you are (你是你是)\n' +
            '[02:46.54]Just one last time again (你就是我的苦思冥想)\n' +
            '[02:49.85]You are you are (你是你是)\n' +
            '[02:54.07]You are the trouble I\'m in (陷入甜蜜烦恼)\n' +
            '[03:01.56]You are the trouble I\'m in (陷入甜蜜烦恼)'
    }]
});
(function () {
    var $wrap = $(".wrapper");
    init();
    function init() {
        /*图片加载延迟执行，不延迟，第二次进入的速度快会直接闪现出现*/
        setTimeout(function () {
            $wrap.removeClass("init");
        }, 200);

        /*执行事件函数*/
        bindEvent();
    }
    function bindEvent() {
        $(".item").on("click", function () {
            $(this).addClass("active");
            $wrap.addClass("startShowItem");
            // setTimeout(function(){
            //     var a = $(".active .inner").css("width");
            //     $(".active .inner .bjPic").css("height", a);
            //     var b = $(".active .inner").css("height");
            //     $(".active .inner .bjPic").css("width", a);
            // }, 500);

        })
        $(".close").on("click", function (e) {
            e.stopPropagation();
            $(".active").removeClass("active");
            $wrap.removeClass("startShowItem");
            
        })
    }
})();

$(document).ready(function() {
    $(".item").mousemove(function(event) {
        var mouseX = event.pageX;
        var mouseY = event.pageY;
        var horzAngle = 0;
        var vertAngle = 0;
        var obj = $(this);
        //Maximum angle 30!
        var objX = obj.offset().left + obj.innerWidth() / 2;
        var objY = obj.offset().top + obj.innerHeight() / 2;

        horzAngle = -((objX - mouseX) / (obj.innerWidth()/2)) * 5;
        vertAngle = ((objY - mouseY) / (obj.innerHeight()/2)) * 5;

        obj.attr("style", "transform: rotateY("+horzAngle+"deg) rotateX("+vertAngle+"deg) translateZ(50px);-webkit-transform: rotateY("+horzAngle+"deg) rotateX("+vertAngle+"deg) translateZ(50px);-moz-transform: rotateY("+horzAngle+"deg) rotateX("+vertAngle+"deg) translateZ(50px)");
    });
    $(".item").mouseout(function() {
        var obj = $(this);
        obj.css({
            '-webkit-transform' : 'rotateY(' + 0 + 'deg)',
            '-moz-transform'    : 'rotateY(' + 0 + 'deg)',
            '-ms-transform'     : 'rotateY(' + 0 + 'deg)',
            '-o-transform'      : 'rotateY(' + 0 + 'deg)',
            'transform'         : 'rotateY(' + 0 + 'deg)'
        });
    });
});

