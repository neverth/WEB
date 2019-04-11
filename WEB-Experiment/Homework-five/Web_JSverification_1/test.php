<?php
  /**
   * Created by IntelliJ IDEA.
   * User: HUSHUHUA
   * Date: 2019/4/11
   * Time: 18:18
   */
  require("php/ConnUtil.php");

  $connUtil = new connUtil();

  $conn = $connUtil->getConn();

  $result  = $conn->query("");
