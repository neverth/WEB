<?php

  class Utils{
    public static function getConnection(){
      $servername = "47.112.25.38";
      $username = "webtest";
      $password = "bXmNTycfEf";
      $dbname = "webtest";
      $conn = new mysqli($servername, $username, $password, $dbname);
      return $conn;
    }
    public static function release($conn){
      $conn->close();
    }
    public static function loginJudgement($location){
      session_start();
      setcookie("PHPSESSID", session_id(), time() + 60 * 30, "/");
      if ($_SESSION['LandingStatus'] != 1) {
        echo "<script>";
        echo "alert('请先登录哦');";
        echo "window.location.href='$location';";
        echo "</script>";
      }
    }
    public static function outputAlert($alert, $location = "../view/sudoManagePage.php"){
      echo "<script>";
      echo "alert('$alert');";
      echo "window.location.href='$location';";
      echo "</script>";
    }
  }
