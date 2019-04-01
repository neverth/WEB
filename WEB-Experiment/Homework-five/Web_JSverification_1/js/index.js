'use strict';

var username = document.getElementById('username');
var myInput = document.getElementById('passw');
var myReinput = document.getElementById('repassw');
var inputCode = document.getElementById('input-code');
var register = document.getElementById('register');
var canvas = document.getElementById('verifi-code');

var code, password, repassword;

myInput.addEventListener('input',function(e){

    let safe_head = document.getElementById('safe-head');
    let safe_d1 = document.getElementById('safe-d1');
    let safe_d2 = document.getElementById('safe-d2');
    let safe_d3 = document.getElementById('safe-d3');
    let input_err = document.getElementById('passw-err');

    console.log("输入密码： " + myInput.value);

    password = myInput.value;
    safe_head.style.visibility = "visible";
    switch (checkPwd(password)) {
        case 0:{
            input_err.innerHTML = "密码不能小于6位";
            safe_d1.style.visibility = "visible";
            safe_d2.style.visibility = "hidden";
            safe_d3.style.visibility = "hidden";
            break;
        }
        case 1:{
            input_err.innerHTML = "";
            safe_d1.style.visibility = "visible";
            safe_d2.style.visibility = "visible";
            safe_d3.style.visibility = "hidden";
            break;
        }
        case 2:{
            input_err.innerHTML = "";
            safe_d1.style.visibility = "visible";
            safe_d2.style.visibility = "visible";
            safe_d3.style.visibility = "visible";
            break;
        }
        default:{
            input_err.innerHTML = "";
        }
    }
});


myReinput.addEventListener('input',function(e){
    let reinput_err = document.getElementById('repassw-err');

    console.log("重复密码输入：" + myReinput.value);

    repassword = myReinput.value;
    if(repassword != password){
        reinput_err.innerHTML = "确认密码不相同哦";
    }
    else{
        reinput_err.innerHTML = "";
    }
});


register.onclick = function () {
    if(username.value){
        if (password) {
            if(password.length >= 6){
                if(myReinput.value){
                    if(myReinput.value == password){
                        if(code == inputCode.value.toUpperCase()){
                            alert("登入成功")
                        } else alert("验证码错误");
                    } else alert("重复密码不相同哦")
                } else alert("请输入重复密码")
            }else alert("请输入符合规范的密码")
        } else alert("请输入密码");
    } else alert("请输入邮箱");
}

function createCode() {
    let code = "";
    var codeLength = 4;
    var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
    for (var i = 0; i < codeLength; i++) {
        var charIndex = Math.floor(Math.random() * 36);
        code += selectChar[charIndex];
    }
    console.log("生成验证码 " + code);
    return code;
}

function draw_canvas(code) {
    if (canvas) {
        var ctx=canvas.getContext('2d');
        ctx.clearRect(0,0,canvas.width,canvas.height);
        ctx.font="80px Verdana";
        ctx.strokeText(code,25,110);
        console.log("canvas 绘制完成")
    }
    else
        console.log("没有找到canvas")
}

function checkPwd(str){
    var pattern1 = /([0-9]+)/i;
    var pattern2 = /([a-z]+)/i;
    if(str.length<6 || str.length>20){
        return 0;
    }
    if(pattern1.exec(str)){
        if(pattern2.exec(str)){
            return 2
        }
        return 1;
    }
}

canvas.onclick = function () {
    code = createCode();
    draw_canvas(code);
}
window.onload = function () {
    code = createCode();
    draw_canvas(code);
}
