<?php

  class Utils{
    public static function getConnection(){
      $servername = "47.112.25.38";
      $username = "webtest";
      $password = "2miLAdrZmj";
      $dbname = "webtest";
      $conn = new mysqli($servername, $username, $password, $dbname);
      return $conn;
    }
    public static function release($conn){
      $conn->close();
    }
  }
