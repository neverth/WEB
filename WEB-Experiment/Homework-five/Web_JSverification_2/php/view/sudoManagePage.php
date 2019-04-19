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
  require_once("../dao/UserDao.php");

  Utils::loginJudgement('./sudoLogin.php');

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

<h1 style="margin-top: 50px"><?= $_SESSION['sudoName'] ?> 欢迎您的登录 这是您的第 <?= $_SESSION['sudoUserCount'] ?> 次登入</h1>
<h1>最近一次登陆时间为 <?= $_SESSION['sudoUserRecLoginTime'] ?> </h1>
<h2>管理中心</h2>
<h2><a href="../controller/LogOff.php">注销</a></h2>
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
    <th colspan="2">操作</th>
  </tr>
  <?

    $UserDao = new UserDao();
    $userList = array();
    $userList = $UserDao->findAll();
    foreach ($userList as $user) {
      $deleteUserUrl = "../controller/userModification.php?category=delete&userId=" . $user->getId();
      $updateUserUrl =
          "../view/userRegister.php?category=update&userId="
          . $user->getId() ."&username=" . $user->getUsername();
      echo "<tr>";
      echo "<th>" . $user->getId() . "</th>";
      echo "<th>" . $user->getUsername() . "</th>";
      echo "<th>" . $user->getPassword() . "</th>";
      echo "<th>" . $user->getEmail() . "</th>";
      echo "<th>" . $user->getSex() . "</th>";
      echo "<th>" . $user->getBirthday() . "</th>";
      echo "<th>" . $user->getHobby() . "</th>";
      echo "<th>" . "<a href='$updateUserUrl'>修改</a>" . "</th>";
      echo "<th>" . "<a href='$deleteUserUrl'>删除</a>" . "</th>";
      echo "</tr>";
    }
  ?>
  </tbody>
</table>
</body>
</html>