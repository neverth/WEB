<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>表单验证</title>
    <link rel="stylesheet" href="../../assets/css/index.css">
</head>
<body>
<header style="color: white;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            background-color: #3c763d;
            padding: 10px;">
  <a style="margin: 10%; color: white" href="./userRegister.php">用户注册</a>
  <a style="margin: 10%; color: white" href="./sudoLogin.php">管理员登录</a>
</header>
<!--    这是内容盒子，控制页面的大小，位置-->
    <div class="contain">
<!--        表单-->
        <form id="userRegisterForm" action="../controller/userModification.php?category=register" method="post">
<!--          用了10个div来布局，里面嵌套input标签和其他内容-->
            <div id="username">用户名：
                <input type="text" required="required" name="username" value="<? echo $_GET['username']?>">
            </div>
            <div>密码：
                <input id="passw" type="password" name="password">
            </div>
            <div>确认密码：
                <input id="repassw" type="password" >
            </div>
            <div>
                <input type="text" style="visibility: hidden;">
            </div>
            <div>
                邮箱：<input type="email" name="email">
            </div>
            <div>
<!--                    div里面在嵌套div放入单选框-->
              <div class="sex">
                性别：<input name="sex" type="radio" class="input-xq" value="1">男
                <input name="sex" type="radio" class="input-xq" value="2">女
              </div>
            </div>
            <div>
                出生日期：<input type="date" value="2011-01-01" name="birthday">
            </div>
            <div>
<!--                span标签用于输出文本，之后再嵌套一个盒子用于输出多选框-->
                <span class="xqhh">
                    兴趣爱好：
                </span>
                <div style="margin-left: 65%; width: 100px; border: none">
                    <input name="hobby1" type="checkbox" class="input-xq" value="1"> 篮球 <br>
                    <input name="hobby2" type="checkbox" class="input-xq" value="1"> 足球 <br>
                    <input name="hobby3" type="checkbox" class="input-xq" value="1"> 养花 <br>
                </div>
            </div>
            <div>
                验证码：
                <input id="input-code" type="text" style="text-align: left; padding-left: 10px">
                <canvas id="verifi-code">
                </canvas>
            </div>
            <div>
                <input id="submit" type="submit" class="input-sub" value="提交" >
                <input type="reset" class="input-sub" value="重置">
            </div>
        </form>
        
    </div>
<script src="../../assets/js/index.js"> </script>

<?php

  if($_GET['userId']){
    echo "<script>";
    echo "let userId = " . $_GET['userId'];
    echo ";let category = '" . $_GET['category'] . "';";
    echo "</script>";

    echo <<<STD
    
    <script>
    let formAction = document.getElementById("userRegisterForm");
    formAction.action = 
    "../controller/userModification.php?category=" + category + "&userId=" + userId;
    </script>
    
STD;
  }
?>
</body>

</html>