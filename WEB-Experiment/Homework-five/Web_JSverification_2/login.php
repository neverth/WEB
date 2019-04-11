<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Document</title>
  <style>
    html,body {
      background: linear-gradient(to left bottom, #DD6455, #a18cd1);
      width: 100%;
      height: 100%;
      color: whitesmoke;
    }

    table.reference, table.tecspec {
      margin-bottom: 4px;
      margin-top: 4px;
      font-size: 18px;
    }
    table.reference th {
      color: #fff;
      /* border: 1px solid #555; */
      padding: 3px;
      text-align: left;
    }
  </style>
</head>
<body>
<?
  require ("php/conn.php");
  $t_username = $_POST["username"];
  $t_password = $_POST["password"];
  $state = 0;

  $sql = "SELECT * FROM user_info";
  $result = $conn->query($sql);
  if ($result->num_rows > 0) {

    while($row = $result->fetch_assoc()) {

      $username = $row["username"];
      $password = $row["password"];
      if(($t_username == $username) && ($t_password == $password)){
        $state = 1;
        break;
      }
    }
    if ($state == 0){
      header("Location: http://localhost/WEB-Experiment/Homework-five/Web_JSverification_2/index.html");
    }
  } else {
    echo "<h2>请向管理员申请账号<h2>";
  }
  session_start();
  if(!$_SESSION['views']){
    $_SESSION['views'] = 1;
  }
  else{
    $_SESSION['views'] += 1;
  }
  $_SESSION['Lastlogontime'] = date('Y-m-d H:i:s',time());
?>

<h1><?=$username ?> 欢迎您的登录 这是您的第 <?=$_SESSION['views'] ?> 次登陆</h1>
<h1>最近一次登陆时间为 <?=$_SESSION['Lastlogontime'] ?> </h1>
<h2>管理中心</h2>
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
    $sql = "SELECT * FROM user_info";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
      // 输出数据
      while($row = $result->fetch_assoc()) {
        echo "<tr>
              <th>{$row["userID"]}</th>
              <th>{$row["username"]}</th>
              <th>{$row["password"]}</th>
              <th>{$row["email"]}</th>
              <th>{$row["sex"]}</th>
              <th>{$row["birthday"]}</th>
              <th>{$row["hobby"]}</th>
              </tr>";
      }
    } else {
      echo "<tr><tr>";
    }
    $conn->close();
  ?>
  </tbody></table>
</body>
</html>