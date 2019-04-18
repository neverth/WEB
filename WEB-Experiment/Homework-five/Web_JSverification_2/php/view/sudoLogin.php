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
  echo session_id();
  setcookie("PHPSESSID", session_id(), time() + 60 * 30, "/");

  if((isset($_SESSION['sudoName']) && (isset($_SESSION['LandingStatus'])))){
    setcookie("sudoName", $_SESSION['sudoName'], time() + 60 * 30, "/");
    header('location: ./sudoManagePage.php');
  }
?>
<form action="../controller/sudoLoginVer.php" method="post">
  用户名<input type="text" name="sudoName" required="required"><br>
  密　码<input type="password" name="sudoPassword" required="required"><br>
  <input type="submit">
</form>

</body>
</html>

