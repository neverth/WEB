<?php
  /**
   * Created by IntelliJ IDEA.
   * User: HUSHUHUA
   * Date: 2019/4/11
   * Time: 23:16
   */

  require_once("../dao/SudoUserDao.php");


  $t_username = $_POST["sudoName"];
  $t_password = $_POST["sudoPassword"];
  $state = 0;

  $sudoUserDao = new SudoUserDao();
  $sudoUserList = array();
  $sudoUserList = $sudoUserDao->findAll();

  foreach ($sudoUserList as $sudoUser) {
    if (($t_username == $sudoUser->getSudoUserName())
        && ($t_password == $sudoUser->getSudoUserPassword())) {
      $state = 1;
      break;
    }
  }

  if ($state == 0) {
    echo "您的密码错误";
    echo "<br><a href='../view/sudoLogin.php'>返回登录界面</a>";
  } 
  else {
    session_start();

    setcookie("sudoName", $t_username, time() + 60 * 30);
    $_SESSION['sudoName'] = $t_username;
    $_SESSION['LandingStatus'] = 1;
    echo "您的密码正确<br>";
    echo "<a href='../view/sudoManagePage.php'>点击进入管理界面</a> <br>";
    echo "<a href='./LogOff.php'>注销</a>";
  }
