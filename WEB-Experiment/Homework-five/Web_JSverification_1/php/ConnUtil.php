<?php
  /**
   * Created by IntelliJ IDEA.
   * User: HUSHUHUA
   * Date: 2019/4/11
   * Time: 18:12
   */


  class connUtil{

    private  $servername = "47.112.25.38";
    private $username = "webtest";
    private $password = "2miLAdrZmj";
    private $dbname = "webtest";

    function getConn(){
      $conn = new mysqli($this->servername, $this->dbname,
          $this->password, $this->dbname);
      if ($conn->connect_error) {
        echo("连接失败: " . $conn->connect_error);
      }
      else {
        echo "连接成功";
      }
      return $conn;
    }
  }