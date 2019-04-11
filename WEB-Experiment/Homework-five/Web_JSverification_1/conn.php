<?php
  /**
   * Created by IntelliJ IDEA.
   * User: HUSHUHUA
   * Date: 2019/4/11
   * Time: 15:55
   */
  $servername = "47.112.25.38";
  $username = "webtest";
  $password = "2miLAdrZmj";
  $dbname = "webtest";

  $conn = new mysqli($servername, $username, $password, $dbname);
  if ($conn->connect_error) {
    die("连接失败: " . $conn->connect_error);
  }
  else {
    echo "连接成功";
  }


  $sql = "SELECT id, firstname, lastname FROM MyGuests";
  $result = $conn->query($sql);

  if ($result->num_rows > 0) {
    // 输出数据
    while($row = $result->fetch_assoc()) {
      echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
    }
  } else {
    echo "0 结果";
  }
  $conn->close();