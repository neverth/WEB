<?php
  /**
   * Created by IntelliJ IDEA.
   * User: HUSHUHUA
   * Date: 2019/4/11
   * Time: 23:16
   */

  require_once("../dao/SudoUserDao.php");

  session_start();
  setcookie("PHPSESSID", session_id(), time() + 60 * 30, "/");

  if((isset($_SESSION['sudoName']) && (isset($_SESSION['LandingStatus'])))){
    setcookie("sudoName", $_SESSION['sudoName'], time() + 60 * 30, "/");
    header('location: ../view/sudoManagePage.php');
  }

  $t_username = $_POST["sudoName"];
  $t_password = $_POST["sudoPassword"];
  $state = 0;
  $sudoUserCount = 0;
  $sudoUserRecLoginTime = "";

  $sudoUserDao = new SudoUserDao();
  $sudoUserList = array();
  $sudoUserList = $sudoUserDao->findAll();

  foreach ($sudoUserList as $sudoUser) {
    if (($t_username == $sudoUser->getSudoUserName())
        && ($t_password == $sudoUser->getSudoUserPassword())) {

      $sudoUserCount = $sudoUser->getSudoCount();
      $sudoUserRecLoginTime = $sudoUser->getSudoUserRecLoginTime();
          
      $sudoUser->setSudoCount(++$sudoUserCount);
      $sudoUserDao->update($sudoUser);

      $state = 1;
      break;
    }
  }

  if ($state == 0) {
    Utils::outputAlert("您的密码错误", "../view/sudoLogin.php");
//    echo "您的密码错误";
//    echo "<br><a href='../view/sudoLogin.php'>返回登录界面</a>";
  } 
  else {

    setcookie("sudoName", $t_username, time() + 60 * 30, "/");
    $_SESSION['sudoName'] = $t_username;
    $_SESSION['LandingStatus'] = 1;
    $_SESSION['sudoUserCount'] = $sudoUserCount;
    $_SESSION['sudoUserRecLoginTime'] = $sudoUserRecLoginTime;
    Utils::outputAlert("登入成功");
//    echo "您的密码正确<br>";
//    echo "<a href='../view/sudoManagePage.php'>点击进入管理界面</a> <br>";
//    echo "<a href='./LogOff.php'>注销</a>";
  }
