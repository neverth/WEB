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
  <?
    $sessionID = $_COOKIE['PHPSESSID'];
    session_start();
    if($_SESSION['LandingStatus'] != 1){
      echo "请先登录哦";
      echo "<br><a href='../php/sudoLogin.php'>返回登录界面</a>";
      exit();
    }
  ?>
<h1><?=$_SESSION['sudoName'] ?> 欢迎您的登录 这是您的第 <?=$_SESSION['views'] ?> 次登陆</h1>
<h1>最近一次登陆时间为 <?=$_SESSION['Lastlogontime'] ?> </h1>
<h2>管理中心</h2> <h2><a href="../php/LogOff.php">注销</a></h2>
<table class="reference" border="1">
  <tbody>
  <tr>
    <th>ID</th>
    <th>username</th>
    <th>password</th>
    <th>email</th>
    <th>sex</th>
    <th>birthday</th>
    <th>hobby</th>
  </tr>
  <?
    require("../dao/UserDao.php");
    $UserDao = new UserDao();
    $userList = array();
    $userList = $UserDao->findAll();
    foreach($userList as $user){
      echo "<tr>";
      echo "<th>" . $user->getId() . "</th>";
      echo "<th>" . $user->getUsername() . "</th>";
      echo "<th>" . $user->getPassword() . "</th>";
      echo "<th>" . $user->getEmail() . "</th>";
      echo "<th>" . $user->getSex() . "</th>";
      echo "<th>" . $user->getBirthday() . "</th>";
      echo "<th>" . $user->getHobby() . "</th>";
      echo "</tr>";
    }
  ?>
  </tbody>
</table>
</body>
</html>