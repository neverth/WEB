<?php
  /**
   * Created by IntelliJ IDEA.
   * User: HUSHUHUA
   * Date: 2019/4/16
   * Time: 20:56
   */
  require_once ("../Utils/Utils.php");
  require_once ("../entity/SudoUser.php");

  class SudoUserDao{
    private $conn;
    private $sql;

    public function __construct() {
      $this->conn = Utils::getConnection();
      $this->sql = "";
    }
    function __destruct() {
      Utils::release($this->conn);
    }

    public function findAll(){
      $sql = "SELECT * FROM `sudo_info`";
      $result = $this->conn->query($sql);
      $userList = array();
      if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
          $SudoUser = new SudoUser();
          $SudoUser->setSudoId($row["sudo_id"]);
          $SudoUser->setSudoUserName($row["sudo_name"]);
          $SudoUser->setSudoUserPassword($row["sudo_password"]);
          array_push($userList, $SudoUser);
        }
      }
      return $userList;
    }
  }