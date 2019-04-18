<?php

  require_once ("../Utils/Utils.php");
  require_once ("../entity/User.php");

class UserDao{
  
    private $conn;
    private $sql;

    public function __construct() {     
        $this->conn = Utils::getConnection();
        $this->sql = "";
    }
    function __destruct() {
        Utils::release($this->conn);
    }
    public function insert($user){
        $sql = "INSERT INTO user_info(
            `username`, `password`, `email`, `sex`, `birthday`, `hobby`)
             VALUES (" 
             . "'" . $user->getUsername() ."', "
             . "'" . $user->getPassword() ."', "
             . "'" . $user->getEmail() ."', "
             . "'" . $user->getSex() ."', "
             . "'" . $user->getBirthday() ."', "
             . "'" . $user->getHobby() ."') ";
      if ($this->conn->query($sql) === TRUE) {
        return TRUE;
      }
      else {
        return false;
      }
    }
    public function findAll(){
      $sql = "SELECT * FROM `user_info`";
      $result = $this->conn->query($sql);
      $userList = array();
      if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
          $user = new User();
          $user->setId($row["userID"]);
          $user->setUsername($row["username"]);
          $user->setPassword($row["password"]);
          $user->setEmail($row["email"]);
          $user->setSex($row["sex"]);
          $user->setBirthday($row["birthday"]);
          $user->setHobby($row["hobby"]);
          array_push($userList, $user);
        }
      }
      return $userList;
    }
    public function update($user){
      $sql = "UPDATE `user_info` SET 
              `username` = '" . $user->getUsername() . "'," .
              "`password` = '" . $user->getPassword(). "'," .
              "`email` = '" . $user->getEmail(). "'," .
              "`sex` = '" . $user->getSex(). "'," .
              "`birthday` = '" . $user->getBirthday(). "'," .
              "`hobby` = '" . $user->getHobby(). "' " .
              "WHERE `userID` = ". $user->getId();
      if ($this->conn->query($sql) === TRUE) {
        return TRUE;
      }
      else {
        return false;
      }
    }
    public function delete($user){
      $sql = "DELETE FROM user_info WHERE userID = ?";
      $stmt = $this->conn->prepare($sql);
      $id = $user->getId();
      $stmt->bind_param('i', $id);
      if($stmt->execute()){
        return true;
      }
      else return false;
    }
}
