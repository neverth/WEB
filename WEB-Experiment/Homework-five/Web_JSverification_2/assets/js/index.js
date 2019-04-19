// 这个地方用于获取页面中的元素和定义全局变量
    
const canvas = document.getElementById('verifi-code');
const submit = document.getElementById('submit');
const myInput = document.getElementById('passw');
const myReinput = document.getElementById('repassw');
const inputCode = document.getElementById('input-code');
let code; // 定于全局的code，主要用于验证码的判断
function createCode() {//生成验证码
    let code = "";
    let codeLength = 4;
    let selectChar = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
    for (let i = 0; i < codeLength; i++) {
        let charIndex = Math.floor(Math.random() * 36);
        code += selectChar[charIndex];
    }
    console.log("生成验证码 " + code);
    return code;
}
function draw_canvas(code) {//把验证码画到canvas上面去
    if (canvas) {
        let ctx=canvas.getContext('2d');
        ctx.clearRect(0,0,canvas.width,canvas.height);
        ctx.font="oblique normal 900 90px arial";
        ctx.strokeText(code,0,110);
        console.log("canvas 绘制完成")
    }
    else
        console.log("没有找到canvas")
}
canvas.onclick = function () {//当点击画布的时候重置验证码
    code = createCode();
    draw_canvas(code);
};
submit.onclick = function(){//对重复密码和验证码的判断
    if(myInput.value === myReinput.value){
        if(inputCode.value.toUpperCase() === code.toUpperCase()) {
            return true;
        } else {
            alert("验证码错误");
            return false;
        } 
    } else {
        alert("密码不相同哦");
        return false;
    };
};
window.onload = function () {//页面初次加载的生成验证码
    code = createCode();
    draw_canvas(code);
}