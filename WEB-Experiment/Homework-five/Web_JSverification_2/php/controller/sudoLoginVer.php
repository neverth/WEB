<?php
  /**
   * Created by IntelliJ IDEA.
   * User: HUSHUHUA
   * Date: 2019/4/11
   * Time: 23:16
   */
  

  require ("./conn.php");
  $t_username = $_POST["sudoName"];
  $t_password = $_POST["sudoPassword"];
  $state = 0;
  $sql = "SELECT * FROM sudo_info";
  $result = $conn->query($sql);
  
  session_start();

  if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
      $username = $row["sudo_name"];
      $password = $row["sudo_password"];
      if(($t_username == $username) && ($t_password == $password)){
        $state = 1;
        break;
      }
    }
    $conn->close();
    if ($state == 0){
      $_SESSION['LandingStatus'] = 0;
      echo "您的密码错误";
      echo "<br><a href='../../view/sudoLogin.php'>返回登录界面</a>";
    }
    else {
      setcookie("sudoName", $t_username, time()+60);
      $_SESSION['sudoName'] = $t_username;
      $_SESSION['LandingStatus'] = 1;
      echo "您的密码正确<br>";
      echo "<a href='../../view/sudoManagePage.php'>点击进入管理界面</a> <br>";
      echo "<a href='./LogOff.php'>注销</a>";
    }
  } 
  else{
    
  }
