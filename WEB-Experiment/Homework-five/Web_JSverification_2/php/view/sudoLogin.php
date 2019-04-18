<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Document</title>
</head>
<body>
<?php
  session_start();
  setcookie("PHPSESSID", session_id(), time() + 60 * 30, "/");

  if((isset($_SESSION['sudoName']) && (isset($_SESSION['LandingStatus'])))){
    setcookie("sudoName", $_SESSION['sudoName'], time() + 60 * 30, "/");
    header('location: ./sudoManagePage.php');
  }
?>
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

<form style="margin-top: 60px" action="../controller/sudoLoginVer.php" method="post">
  用户名<input type="text" name="sudoName" required="required"><br>
  密　码<input type="password" name="sudoPassword" required="required"><br>
  <input type="submit">
</form>

</body>
</html>

